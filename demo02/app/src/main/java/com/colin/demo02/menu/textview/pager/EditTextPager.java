package com.colin.demo02.menu.textview.pager;

import android.content.Context;
import android.view.View;

import com.colin.demo02.R;
import com.colin.demo02.menu.base.BasePager;

public class EditTextPager extends BasePager {
    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public EditTextPager(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.activity_edit_text, null);
        return view;
    }
}
