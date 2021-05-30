package com.colin.demo02.menu.textview.pager;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.colin.demo02.R;
import com.colin.demo02.menu.base.BasePager;

public class CompoundButtonPager extends BasePager implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "CompoundButtonPager";

    private RadioGroup mRadioGroup;

    private CheckBox mEat;

    private CheckBox mSleep;

    private ToggleButton mToggleButton;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public CompoundButtonPager(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.compound_button, null);
        mRadioGroup = view.findViewById(R.id.radio_group_demo);
        RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.men:
                        break;
                    case R.id.women:
                        break;
                }
            }
        };
        mRadioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        mEat = view.findViewById(R.id.eat);
        mSleep = view.findViewById(R.id.sleep);
        mEat.setOnCheckedChangeListener(this);
        mSleep.setOnCheckedChangeListener(this);
        mToggleButton = view.findViewById(R.id.toggle_button);
        mToggleButton.setOnCheckedChangeListener((toggleView, isChecked) -> {
            if (isChecked) {
            } else {
            }
        });
        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.e(TAG, "buttomView id: " + buttonView.getId());
    }
}
