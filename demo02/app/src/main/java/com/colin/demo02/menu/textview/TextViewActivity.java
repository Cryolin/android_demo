package com.colin.demo02.menu.textview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.colin.demo02.R;

public class TextViewActivity extends AppCompatActivity {
    private TextView mMarquee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_text_view);
        setContentView(R.layout.text_view_guide);
        initView();
    }

    private void initView() {
//        mMarquee = findViewById(R.id.marquee);
//        mMarquee.setSelected(true);
    }
}