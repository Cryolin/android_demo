package com.colin.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.colin.aidllib.IMediaPlayer;
import com.colin.aidllib.IMediaPlayerClient;
import com.colin.aidllib.IMediaPlayerService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBind;

    private Button mCreateMediaPlayer;

    private Button mPlay;

    private Button mPause;

    private IMediaPlayer mMediaPlayer;

    private boolean mIsBound;

    private IMediaPlayerService mService;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = IMediaPlayerService.Stub.asInterface(iBinder);
            mIsBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
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

    private void initView() {
        mBind = findViewById(R.id.bind);
        mBind.setOnClickListener(this);
        mCreateMediaPlayer = findViewById(R.id.crate_media_player);
        mCreateMediaPlayer.setOnClickListener(this);
        mPlay = findViewById(R.id.play);
        mPlay.setOnClickListener(this);
        mPause = findViewById(R.id.pause);
        mPause.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bind:
                Intent intent = new Intent();
                intent.setClassName("com.colin.server", "com.colin.server.MyService");
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.crate_media_player:
                if (!mIsBound || mService == null) {
                    Toast.makeText(getApplicationContext(), "请先bindService", Toast.LENGTH_LONG).show();
                    break;
                }
                try {
                    mMediaPlayer = mService.createMediaPlayer(new IMediaPlayerClient.Stub() {
                        @Override
                        public void onNotify(String notifyData) throws RemoteException {
                            Toast.makeText(getApplicationContext(), notifyData, Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.play:
                if (!mIsBound || mService == null) {
                    Toast.makeText(getApplicationContext(), "请先bindService", Toast.LENGTH_LONG).show();
                    break;
                }
                if (mMediaPlayer == null) {
                    Toast.makeText(getApplicationContext(), "请先创建MediaPlayer", Toast.LENGTH_LONG).show();
                    break;
                }
                try {
                    mMediaPlayer.play();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.pause:
                if (!mIsBound || mService == null) {
                    Toast.makeText(getApplicationContext(), "请先bindService", Toast.LENGTH_LONG).show();
                    break;
                }
                if (mMediaPlayer == null) {
                    Toast.makeText(getApplicationContext(), "请先创建MediaPlayer", Toast.LENGTH_LONG).show();
                    break;
                }
                try {
                    mMediaPlayer.pause();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}