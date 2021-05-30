package com.colin.demo02.menu.textview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.colin.demo02.R;
import com.colin.demo02.menu.base.BaseFragment;
import com.colin.demo02.menu.base.BasePager;
import com.colin.demo02.menu.textview.pager.EditTextPager;
import com.colin.demo02.menu.textview.pager.TextViewPager;

import java.util.ArrayList;
import java.util.List;

public class TextViewActivity extends AppCompatActivity {


    private List<BasePager> mPagers = new ArrayList<>();

    private RadioGroup mRadioGroup;

    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_text_view);
        setContentView(R.layout.text_view_guide);
        initView();
    }

    private void setFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_text_view, new BaseFragment(mPagers.get(mPosition)));
        fragmentTransaction.commit();
    }

    private void initView() {
        mPagers.add(new TextViewPager(this));
        mPagers.add(new EditTextPager(this));
        mRadioGroup = findViewById(R.id.text_view_radio_group);
        mRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.text_view:
                    mPosition = 0;
                    break;
                case R.id.edit_text:
                    mPosition = 1;
                    break;
                case R.id.button:
                    mPosition = 2;
                    break;
                case R.id.image_button:
                    mPosition = 3;
                    break;
                default:
                    break;
            }
            setFragment();
        });
        mRadioGroup.check(R.id.text_view);
//        mMarquee = findViewById(R.id.marquee);
//        mMarquee.setSelected(true);
    }
}