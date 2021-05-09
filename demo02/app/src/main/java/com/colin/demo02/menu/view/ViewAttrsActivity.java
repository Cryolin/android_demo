package com.colin.demo02.menu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.colin.demo02.R;

/**
 * 演示View的属性
 */
public class ViewAttrsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attrs);
    }

    public void clickMe(View view) {
        Toast.makeText(this, "设置了clickable=true后，本控件可点击", Toast.LENGTH_LONG).show();
    }
}