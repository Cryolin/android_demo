package com.colin.demo02.util;

import android.app.Activity;
import android.content.Intent;

import com.colin.demo02.MainActivity;
import com.colin.demo02.menu.layout.LayoutActivity;

public class JumpActivityUtil {
    public static void jumpActivity(Activity fromActivity, Class toClass) {
        Intent intent = new Intent(fromActivity, toClass);
        fromActivity.startActivity(intent);
    }
}
