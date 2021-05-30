package com.colin.demo02.menu.base;

import android.content.Context;
import android.view.View;

public abstract class BasePager {
    /**
     * 上下文
     */
    public Context mContext;
    private View mRootView;
    private boolean mHasInit;


    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public BasePager(Context context) {
        mContext = context;
        mRootView = initView();
    }

    /**
     * 初始化根View
     *
     * @return 根View
     */
    protected abstract View initView();

    /**
     * 按需实现，初始化数据
     */
    public void initData() {
    }

    public View getRootView() {
        return mRootView;
    }

    public boolean hasInit() {
        return mHasInit;
    }

    public void setHasInit(boolean hasInit) {
        this.mHasInit = hasInit;
    }
}
