package com.colin.demo02.menu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.colin.demo02.MainActivity;
import com.colin.demo02.R;
import com.colin.demo02.util.JumpActivityUtil;

public class ViewActivity extends AppCompatActivity {
    private Button mViewAttrs;
    private Button mViewGroupLayoutParamsAttrs;
    private Button mViewGroupMarginLayoutParamsAttrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initView();
    }

    private void initView() {
        mViewAttrs = findViewById(R.id.view_attrs);
        mViewAttrs.setOnClickListener((v) -> JumpActivityUtil.jumpActivity(ViewActivity.this, ViewAttrsActivity.class));

        mViewGroupLayoutParamsAttrs = findViewById(R.id.viewgroup_layoutparams_attrs);
        mViewGroupLayoutParamsAttrs.setOnClickListener((v) -> JumpActivityUtil.jumpActivity(ViewActivity.this, ViewGroupLayoutParamsActivity.class));

        mViewGroupMarginLayoutParamsAttrs = findViewById(R.id.viewgroup_marginlayoutparams_attrs);
        mViewGroupMarginLayoutParamsAttrs.setOnClickListener((v) -> JumpActivityUtil.jumpActivity(ViewActivity.this, ViewGroupMarginLayoutParamsActivity.class));
    }
}