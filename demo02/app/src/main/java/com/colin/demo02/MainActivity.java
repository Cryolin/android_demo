package com.colin.demo02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.colin.demo02.menu.layout.LayoutActivity;
import com.colin.demo02.menu.textview.EditTextActivity;
import com.colin.demo02.menu.textview.TextViewActivity;
import com.colin.demo02.menu.view.ViewActivity;
import com.colin.demo02.util.JumpActivityUtil;

public class MainActivity extends AppCompatActivity {
    private Button mViewBtn;
    private Button mLayoutBtn;
    private Button mTextViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewBtn = findViewById(R.id.view_activity);
        mViewBtn.setOnClickListener((v) -> JumpActivityUtil.jumpActivity(MainActivity.this, ViewActivity.class));
        mLayoutBtn = findViewById(R.id.layout_activity);
        mLayoutBtn.setOnClickListener((v) -> JumpActivityUtil.jumpActivity(MainActivity.this, LayoutActivity.class));
        mTextViewBtn = findViewById(R.id.textView_activity);
        mTextViewBtn.setOnClickListener((v)-> JumpActivityUtil.jumpActivity(MainActivity.this, EditTextActivity.class));
    }
}