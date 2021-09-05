package com.colin.mylibrary;

import com.colin.mylibrary.Student;
import com.colin.mylibrary.INotifyCallback;

interface IRemoteInterface {
    void join(IBinder token, in Student student);

    void leave(IBinder token);

    void registerNotifyCallback(INotifyCallback callback);

    void unregisterNotifyCallback(INotifyCallback callback);
}