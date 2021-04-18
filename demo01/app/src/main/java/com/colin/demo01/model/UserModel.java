package com.colin.demo01.model;

import com.colin.demo01.model.entity.UserBean;
import com.colin.demo01.presenter.listener.ILoadRandomUser;
import com.colin.demo01.presenter.listener.ILogin;

import java.util.Random;

public class UserModel implements BaseModel {
    @Override
    public void loadRandomUserName(ILoadRandomUser listener) {
        String randomUserName = "" + new Random().nextInt(100);
        listener.onLoadSucc(randomUserName);
    }

    @Override
    public void login(UserBean userBean, ILogin iLogin) {
        varifyUserInfo();
        saveUserBean();
        iLogin.onLoginSucc(userBean.getToken());
    }

    private boolean varifyUserInfo() {
        // TODO
        return true;
    }

    private void saveUserBean() {
        // TODO
    }
}
