package com.colin.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.colin.aidllib.IMediaPlayerClient;
import com.colin.aidllib.IMediaPlayer;
import com.colin.aidllib.IMediaPlayerService;

public class MyService extends Service {
    private IMediaPlayerClient mClient;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IMediaPlayerService.Stub() {

            @Override
            public IMediaPlayer createMediaPlayer(IMediaPlayerClient client) throws RemoteException {
                mClient = client;
                return new Client();
            }
        };
    }

    class Client extends IMediaPlayer.Stub {
        @Override
        public void play() throws RemoteException {
            if (mClient != null) {
                mClient.onNotify("start play!!!");
            }
        }

        @Override
        public void pause() throws RemoteException {
            if (mClient != null) {
                mClient.onNotify("pause!!!");
            }
        }
    }
}