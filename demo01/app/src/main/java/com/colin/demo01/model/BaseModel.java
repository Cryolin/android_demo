package com.colin.demo01.model;

import android.text.Editable;

import com.colin.demo01.model.entity.UserBean;
import com.colin.demo01.presenter.listener.ILoadRandomUser;
import com.colin.demo01.presenter.listener.ILogin;

public interface BaseModel {
    void loadRandomUserName(ILoadRandomUser listener);

    void login(UserBean user, ILogin iLogin);
}
