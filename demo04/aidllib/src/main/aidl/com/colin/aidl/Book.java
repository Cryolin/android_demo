package com.colin.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable{
    public String mBookName;
    public int mId;

    public Book(String bookName, int id) {
        mBookName = bookName;
        mId = id;
    }

    /**
     * 构造方法，将Parcel对象转化为Book对象
     *
     * @param in
     */
    protected Book(Parcel in) {
        mBookName = in.readString();
        mId = in.readInt();
    }

    // 把当前Book对象写入Parcel对象中
    // 注意读和写的顺序要一致
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mBookName);
        parcel.writeInt(mId);
    }

    /**
     * 实现Parcelable接口必须创建CREATOR
     * 会回调其中的方法进行对象创建
     */
    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    // 这个方法不用管
    @Override
    public int describeContents() {
        return 0;
    }
}
