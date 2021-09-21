package com.colin.mediaplayerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mPlay = findViewById(R.id.play);
        mPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                try {
                    MediaPlayer mp = new MediaPlayer();
                    mp.setOnPreparedListener(mediaPlayer -> mediaPlayer.start());
                    AssetManager assetMg = this.getApplicationContext().getAssets();
                    AssetFileDescriptor fileDescriptor = null;
                    fileDescriptor = assetMg.openFd("music.mp3");
                    mp.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}