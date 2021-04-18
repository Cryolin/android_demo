package com.colin.demo01.presenter;

import android.graphics.ColorSpace;
import android.text.Editable;

import com.colin.demo01.model.BaseModel;
import com.colin.demo01.model.UserModel;
import com.colin.demo01.model.entity.UserBean;
import com.colin.demo01.presenter.listener.ILoadRandomUser;
import com.colin.demo01.presenter.listener.ILogin;
import com.colin.demo01.view.ViewInterface;

public class BasePresenter implements ILoadRandomUser, ILogin {
    private ViewInterface mView;
    private BaseModel mUserModel;

    public BasePresenter(ViewInterface view) {
        mView = view;
        mUserModel = new UserModel();
    }

    public void loadRandomUserName() {
        mUserModel.loadRandomUserName(this);
    }

    public void login(String userName, String pwd) {
        mUserModel.login(new UserBean(userName, pwd), this);
    }

    @Override
    public void onLoadSucc(String randomUser) {
        mView.showRandomUser(randomUser);
    }

    @Override
    public void onLoginSucc(String userToken) {
        mView.startUserSpace(userToken);
    }
}
