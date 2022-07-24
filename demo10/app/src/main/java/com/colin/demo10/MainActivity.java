package com.colin.demo10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.colin.demo10.databinding.ActivityMainBinding;
import com.colin.demo10.entity.FruitEntity;
import com.colin.demo10.entity.VegeEntity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        FruitEntity fruitEntity = new FruitEntity("abc");
        VegeEntity vegeEntity = new VegeEntity("ccc");
        mDataBinding.setFruit(fruitEntity);
        mDataBinding.setVege(vegeEntity);
    }
}