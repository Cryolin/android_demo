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

import com.colin.aidllib.IRemoteInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBind;

    private Button mGetName;

    private boolean mIsBound;

    private IRemoteInterface mService;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = IRemoteInterface.Stub.asInterface(iBinder);
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
        mGetName = findViewById(R.id.getName);
        mGetName.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bind:
                Intent intent = new Intent();
                intent.setClassName("com.colin.server", "com.colin.server.MyService");
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.getName:
                if (!mIsBound || mService == null) {
                    Toast.makeText(getApplicationContext(), "请先bindService", Toast.LENGTH_LONG).show();
                    break;
                }
                try {
                    String name = mService.getName();
                    Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}