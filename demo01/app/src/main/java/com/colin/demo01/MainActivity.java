package com.colin.demo01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.colin.demo01.presenter.BasePresenter;
import com.colin.demo01.view.ViewInterface;

public class MainActivity extends Activity implements ViewInterface {
    private CheckBox mCheckBox;

    private EditText mUserNameEditText;

    private EditText mPwdEditText;

    private Button mLoginBtn;

    private BasePresenter mPresenter;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPresenter();
        initOnClickListener();
    }

    private void initView() {
        mCheckBox = findViewById(R.id.checkbox);
        mUserNameEditText = findViewById(R.id.user_name);
        mLoginBtn = findViewById(R.id.login);
        mPwdEditText = findViewById(R.id.pwd);
    }

    private void initPresenter() {
        mPresenter = new BasePresenter(this);
    }

    private void initOnClickListener() {
        mCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    mPresenter.loadRandomUserName();
                }
            }
        );

        mLoginBtn.setOnClickListener((view)->{
            mPresenter.login(mUserNameEditText.getText().toString(), mPwdEditText.getText().toString());
        });
    }

    @Override
    public void showRandomUser(String randomUser) {
        mUserNameEditText.setText(randomUser);
    }

    @Override
    public void startUserSpace(String userToken) {
        Intent intent = new Intent(this, UserSpaceActivity.class);
        intent.putExtra("token", userToken);
        startActivity(intent);
        finish();
    }
}