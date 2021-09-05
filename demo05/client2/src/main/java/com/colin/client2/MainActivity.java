package com.colin.client2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.colin.mylibrary.INotifyCallback;
import com.colin.mylibrary.IRemoteInterface;
import com.colin.mylibrary.Student;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity client 1";

    private Button mBindBtn;

    private EditText mInputStudentNameEditText;

    private Button mJoinBtn;

    private Button mLeaveBtn;

    private Button mRegisterBtn;

    private Button mUnregisterBtn;

    private IRemoteInterface mService;

    private boolean mIsBound;

    private IBinder mToken = new Binder();

    private INotifyCallback mNotifyCallback = new INotifyCallback.Stub() {
        @Override
        public void onNotify(Student student, boolean isJoinOrLeave) throws RemoteException {
            String joinOrLeave = isJoinOrLeave ? "加入" : "离开";
            Log.e(TAG, " Student ：" + student.mName + joinOrLeave + "班级");
        }
    };

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e(TAG, "bindService 成功");
            mIsBound = true;
            mService = IRemoteInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(TAG, "bindService 异常");
            mIsBound = false;
            mService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 点击空白处EditText失去焦点
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mInputStudentNameEditText.getWindowToken(), 0);
        return super.onTouchEvent(event);
    }

    private void initView() {
        mBindBtn = findViewById(R.id.bind);
        mInputStudentNameEditText = findViewById(R.id.inputStudentName);
        mJoinBtn = findViewById(R.id.join);
        mLeaveBtn = findViewById(R.id.leave);
        mRegisterBtn = findViewById(R.id.registerButton);
        mUnregisterBtn = findViewById(R.id.unregisterButton);
        mBindBtn.setOnClickListener(this);
        mJoinBtn.setOnClickListener(this);
        mLeaveBtn.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
        mUnregisterBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bind:
                Intent intent = new Intent();
                intent.setClassName("com.colin.server", "com.colin.server.MainService");
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.join:
                if (!mIsBound || mService == null) {
                    Log.e(TAG, "join 失败，请先bindService");
                    break;
                }
                String studentName = mInputStudentNameEditText.getText().toString();
                if (!TextUtils.isEmpty(studentName)) {
                    try {
                        mService.join(mToken, new Student(studentName));
                        Log.e(TAG, "报名成功");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.leave:
                if (!mIsBound || mService == null) {
                    Log.e(TAG, "leave 失败，请先bindService");
                    break;
                }
                try {
                    mService.leave(mToken);
                    Log.e(TAG, "取消报名成功");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.registerButton:
                if (!mIsBound || mService == null) {
                    Log.e(TAG, "register 失败，请先bindService");
                    break;
                }
                try {
                    mService.registerNotifyCallback(mNotifyCallback);
                    Log.e(TAG, "registerNotifyCallback");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.unregisterButton:
                if (!mIsBound || mService == null) {
                    Log.e(TAG, "unregister 失败，请先bindService");
                    break;
                }
                try {
                    mService.unregisterNotifyCallback(mNotifyCallback);
                    Log.e(TAG, "unregisterNotifyCallback");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}