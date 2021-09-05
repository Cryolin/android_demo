package com.colin.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.colin.mylibrary.INotifyCallback;
import com.colin.mylibrary.IRemoteInterface;
import com.colin.mylibrary.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个代码主要演示binderDied以及RemoteCallbackList
 * 实际上这个代码还存在一些问题
 * 例如，在binderDied的时候，没有清理Client的callback，仍然会调用Client的callback试图通知已经die的Client
 * 此时会报如下异常：
 *
 * 2021-09-05 14:35:46.453 32246-32278/com.colin.server W/System.err: android.os.DeadObjectException
 * 2021-09-05 14:35:46.456 32246-32278/com.colin.server W/System.err:     at android.os.BinderProxy.transactNative(Native Method)
 * 2021-09-05 14:35:46.456 32246-32278/com.colin.server W/System.err:     at android.os.BinderProxy.transact(BinderProxy.java:540)
 * 2021-09-05 14:35:46.456 32246-32278/com.colin.server W/System.err:     at com.colin.mylibrary.INotifyCallback$Stub$Proxy.onNotify(INotifyCallback.java:107)
 * 2021-09-05 14:35:46.456 32246-32278/com.colin.server W/System.err:     at com.colin.server.MainService.notifyCallback(MainService.java:83)
 * 2021-09-05 14:35:46.456 32246-32278/com.colin.server W/System.err:     at com.colin.server.MainService.access$100(MainService.java:17)
 * 2021-09-05 14:35:46.456 32246-32278/com.colin.server W/System.err:     at com.colin.server.MainService$StudentClient.binderDied(MainService.java:115)
 * 2021-09-05 14:35:46.456 32246-32278/com.colin.server W/System.err:     at android.os.IBinder$DeathRecipient.binderDied(IBinder.java:305)
 * 2021-09-05 14:35:46.457 32246-32278/com.colin.server W/System.err:     at android.os.BinderProxy.sendDeathNotice(BinderProxy.java:654)
 *
 */
public class MainService extends Service {
    private static final String TAG = "MainService";

    private List<StudentClient> mClients = new ArrayList<>();

    private RemoteCallbackList<INotifyCallback> mCallback = new RemoteCallbackList<>();

    public MainService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IRemoteInterface.Stub() {
            @Override
            public void join(IBinder token, Student student) throws RemoteException {
                if (mClients.contains(token)) {
                    Log.e(TAG, " 已报名，无需重复报名");
                    return;
                }
                Log.e(TAG, "报名成功");
                StudentClient studentClient = new StudentClient(token, student);

                // 注册客户端死掉的通知
                token.linkToDeath(studentClient, 0);
                mClients.add(studentClient);

                notifyCallback(student, true);
            }

            @Override
            public void leave(IBinder token) throws RemoteException {
                int idx = findClient(token);
                if (idx < 0) {
                    Log.e(TAG, " 已取消报名，无需重复取消");
                    return;
                }

                Log.e(TAG, "取消报名成功");
                StudentClient client = mClients.get(idx);
                mClients.remove(client);

                notifyCallback(client.mStudent, false);

                // 取消注册
                client.mToken.unlinkToDeath(client, 0);

            }

            @Override
            public void registerNotifyCallback(INotifyCallback callback) throws RemoteException {
                Log.e(TAG, "收到您的监听请求，学生变化时会通知您");
                mCallback.register(callback);
            }

            @Override
            public void unregisterNotifyCallback(INotifyCallback callback) throws RemoteException {
                Log.e(TAG, "收到您的取消监听请求，学生变化不会再通知您");
                mCallback.unregister(callback);
            }
        };
    }

    private void notifyCallback(Student student, boolean isJoin) {
        final int len = mCallback.beginBroadcast();
        for (int i = 0; i < len; i++) {
            try {
                mCallback.getBroadcastItem(i).onNotify(student, isJoin);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mCallback.finishBroadcast();
    }

    private int findClient(IBinder token) {
        for (int i = 0; i < mClients.size(); i++) {
            if (mClients.get(i).mToken == token) {
                return i;
            }
        }
        return -1;
    }

    private final class StudentClient implements IBinder.DeathRecipient {
        public final IBinder mToken;
        public final Student mStudent;

        StudentClient(IBinder token, Student student) {
            mToken = token;
            mStudent = student;
        }

        @Override
        public void binderDied() {
            // 客户端异常死掉，执行此回调，清理资源
            if (mClients.contains(this)) {
                Log.e(TAG, "binder died for ： " + mStudent.mName);
                mClients.remove(this);
                notifyCallback(mStudent, false);
            }
        }
    }
}