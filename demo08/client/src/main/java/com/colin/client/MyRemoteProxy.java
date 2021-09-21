package com.colin.client;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.colin.aidl.IRemoteInterface;

public class MyRemoteProxy implements IRemoteInterface {
    // 客户端需要持有IBinder（实际上是BinderProxy）
    private IBinder mRemote;

    public MyRemoteProxy(IBinder iBinder) {
        mRemote = iBinder;
    }

    // 客户端通过mRemote.transact() 调用服务端
    @Override
    public String getName() throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        String _result;
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            mRemote.transact(TRANSACTION_getName, _data, _reply, 0);
            _reply.readException();
            _result = _reply.readString();
        }
        finally {
            _reply.recycle();
            _data.recycle();
        }
        return _result;
    }

    // 客户端返回mRemote
    @Override
    public IBinder asBinder() {
        return mRemote;
    }
}
