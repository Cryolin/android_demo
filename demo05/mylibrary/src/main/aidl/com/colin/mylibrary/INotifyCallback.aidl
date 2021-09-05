package com.colin.mylibrary;

import com.colin.mylibrary.Student;

interface INotifyCallback {
    void onNotify(in Student student, boolean isJoinOrLeave);
}