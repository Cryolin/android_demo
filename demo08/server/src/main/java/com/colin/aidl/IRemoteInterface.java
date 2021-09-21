package com.colin.aidl;

import android.os.IInterface;
import android.os.RemoteException;

public interface IRemoteInterface extends IInterface {
    String DESCRIPTOR = "com.colin.aidl.IRemoteInterface";

    int TRANSACTION_getName = android.os.IBinder.FIRST_CALL_TRANSACTION + 0;

    String getName() throws RemoteException;
}
