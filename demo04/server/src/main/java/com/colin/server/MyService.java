package com.colin.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.colin.aidl.Book;
import com.colin.aidl.IRemoteInterface;

import java.util.HashMap;
import java.util.Map;

public class MyService extends Service {
    private static final String TAG = "MyService";
    private Map<Integer, Book> mBookMap = new HashMap<>();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IRemoteInterface.Stub() {
            @Override
            public String getName() throws RemoteException {
                return "你好，我是服务端";
            }

            @Override
            public Book queryBook(int id) throws RemoteException {
                if (id < 0 || id > 10) {
                    Log.e(TAG, "invalid id");
                    return null;
                }
                return mBookMap.containsKey(id) ? mBookMap.get(id) : null;
            }

            @Override
            public boolean addBook(Book book) throws RemoteException {
                if (book == null || book.mId < 0 || book.mId > 10 || TextUtils.isEmpty(book.mBookName)) {
                    Log.e(TAG, "invalid book, add fail");
                    return false;
                }
                if (mBookMap.containsKey(book.mId)) {
                    Log.e(TAG, "already contained specified id, pls reedit id and try again");
                    return false;
                }
                mBookMap.put(book.mId, book);
                return true;
            }
        };
    }
}