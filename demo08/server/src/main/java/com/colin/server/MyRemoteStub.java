package com.colin.server;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.colin.aidl.IRemoteInterface;

public class MyRemoteStub extends Binder implements IRemoteInterface {
    @Override
    public String getName() throws RemoteException {
        return "This is server";
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code)
        {
            case INTERFACE_TRANSACTION:
            {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_getName:
            {
                data.enforceInterface(DESCRIPTOR);
                java.lang.String _result = this.getName();
                reply.writeNoException();
                reply.writeString(_result);
                return true;
            }
            default:
            {
                return super.onTransact(code, data, reply, flags);
            }
        }
    }
}
