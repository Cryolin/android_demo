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

import com.colin.aidl.IRemoteInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBind;

    private Button mGetName;

    private boolean mIsBound;

    private IRemoteInterface mService;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mIsBound = true;
            mService = new MyRemoteProxy(iBinder);
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

    private void initView() {
        mBind = findViewById(R.id.bind);
        mBind.setOnClickListener(this);
        mGetName = findViewById(R.id.get_name);
        mGetName.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bind:
                if (!mIsBound && mService == null) {
                    Intent intent = new Intent();
                    intent.setClassName("com.colin.server", "com.colin.server.MyService");
                    bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                }
                break;
            case R.id.get_name:
                if (!mIsBound || mService == null) {
                    Toast.makeText(getApplicationContext(), "请先bindService", Toast.LENGTH_SHORT).show();
                    break;
                }
                try {
                    String name = mService.getName();
                    Toast.makeText(getApplicationContext(), "name is : " + name, Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}