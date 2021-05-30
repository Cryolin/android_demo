package com.colin.demo02.menu.textview.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.colin.demo02.R;
import com.colin.demo02.menu.base.BasePager;

public class TextViewPager extends BasePager {
    private TextView mMarquee;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public TextViewPager(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.activity_text_view, null);
        mMarquee = view.findViewById(R.id.marquee);
        mMarquee.setSelected(true);
        return view;
    }
}
