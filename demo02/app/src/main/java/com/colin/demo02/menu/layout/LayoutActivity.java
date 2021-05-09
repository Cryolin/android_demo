package com.colin.demo02.menu.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.colin.demo02.R;
import com.colin.demo02.util.JumpActivityUtil;

public class LayoutActivity extends AppCompatActivity {
    private Button mLinearBtn;
    private Button mRelativeBtn;
    private Button mTableBtn;
    private Button mFrameBtn;
    private Button mGridBtn;
    private Button mAbsoluteBtn;
    private Button mConstraintBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        initView();
    }

    private void initView() {
        mLinearBtn = findViewById(R.id.linear_layout);
        mLinearBtn.setOnClickListener((v)-> JumpActivityUtil.jumpActivity(LayoutActivity.this, LinearLayoutActivity.class));

        mRelativeBtn = findViewById(R.id.relative_layout);
        mRelativeBtn.setOnClickListener((v)-> JumpActivityUtil.jumpActivity(LayoutActivity.this, RelativeLayoutActivity.class));

        mTableBtn = findViewById(R.id.table_layout);
        mTableBtn.setOnClickListener((v)-> JumpActivityUtil.jumpActivity(LayoutActivity.this, TableLayoutActivity.class));

        mFrameBtn = findViewById(R.id.frame_layout);
        mFrameBtn.setOnClickListener((v)-> JumpActivityUtil.jumpActivity(LayoutActivity.this, FrameLayoutActivity.class));

        mGridBtn = findViewById(R.id.grid_layout);
        mGridBtn.setOnClickListener((v)-> JumpActivityUtil.jumpActivity(LayoutActivity.this, GridLayoutActivity.class));

        mAbsoluteBtn = findViewById(R.id.absolute_layout);
        mAbsoluteBtn.setOnClickListener((v)-> JumpActivityUtil.jumpActivity(LayoutActivity.this, AbsoluteLayoutActivity.class));

        mConstraintBtn = findViewById(R.id.constraint_layout);
        mConstraintBtn.setOnClickListener((v)-> JumpActivityUtil.jumpActivity(LayoutActivity.this, ConstraintLayoutActivity.class));
    }
}