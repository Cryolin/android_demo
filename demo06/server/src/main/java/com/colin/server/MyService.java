package com.colin.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.colin.aidllib.IRemoteInterface;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IRemoteInterface.Stub() {
            @Override
            public String getName() throws RemoteException {
                return "Hello, I'm Server!";
            }
        };
    }
}