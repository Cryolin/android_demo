package com.colin.demo02.menu.textview.pager;

import android.content.Context;
import android.view.View;

import com.colin.demo02.R;
import com.colin.demo02.menu.base.BasePager;

public class ButtonPager extends BasePager {
    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public ButtonPager(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        return View.inflate(mContext, R.layout.button_layout, null);
    }
}
