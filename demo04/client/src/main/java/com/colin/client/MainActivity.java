package com.colin.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.colin.aidl.Book;
import com.colin.aidl.IRemoteInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private IRemoteInterface mAidlInterface;

    private TextView mTextView;

    private EditText mAddBookIdEditText;

    private EditText mAddBookNameEditText;

    private EditText mQueryBookEditText;

    private Button mBind;

    private Button mUnbind;

    private Button mGetName;

    private Button mAddBook;

    private Button mQueryBook;

    private boolean mIsBound;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(getApplicationContext(), "bindService 成功", Toast.LENGTH_SHORT).show();
            mIsBound = true;
            mAidlInterface = IRemoteInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIsBound = false;
            Toast.makeText(getApplicationContext(), "bindService 异常失败", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mIsBound) {
            unbindService(mServiceConnection);
        }
    }

    private void initView() {
        mTextView = findViewById(R.id.textView);
        mBind = findViewById(R.id.bind);
        mBind.setOnClickListener(this);
        mUnbind = findViewById(R.id.unbind);
        mUnbind.setOnClickListener(this);
        mGetName = findViewById(R.id.getName);
        mGetName.setOnClickListener(this);
        mAddBookIdEditText = findViewById(R.id.addBookIdEditText);
        mAddBookNameEditText = findViewById(R.id.addBookNameEditText);
        mAddBook = findViewById(R.id.addBook);
        mAddBook.setOnClickListener(this);
        mQueryBookEditText = findViewById(R.id.querybookEditText);
        mQueryBook = findViewById(R.id.querybook);
        mQueryBook.setOnClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 点击空白处EditText失去焦点
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mAddBookIdEditText.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mAddBookNameEditText.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mQueryBookEditText.getWindowToken(), 0);
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bind:
                Intent intent = new Intent();
                intent.setClassName("com.colin.server", "com.colin.server.MyService");
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                if (mIsBound) {
                    unbindService(mServiceConnection);
                    mIsBound = false;
                    mAidlInterface = null;
                }
                Toast.makeText(getApplicationContext(), "unbindService ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.getName:
                getNameInner();
                break;
            case R.id.addBook:
                addBookInner();
                break;
            case R.id.querybook:
                queryBookInner();
                break;
            default:
                break;
        }
    }

    private void getNameInner() {
        if (!mIsBound || mAidlInterface == null) {
            Toast.makeText(getApplicationContext(), "请先bindService", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Toast.makeText(getApplicationContext(), "调用服务端的getName()返回： " + mAidlInterface.getName(), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void addBookInner() {
        if (!mIsBound || mAidlInterface == null) {
            Toast.makeText(getApplicationContext(), "请先bindService", Toast.LENGTH_SHORT).show();
            return;
        }
        String bookIdStr = mAddBookIdEditText.getText().toString();
        int bookId;
        try {
            bookId = Integer.valueOf(bookIdStr);
        } catch (NumberFormatException e) {
            bookId = 0;
        }
        String bookName = mAddBookNameEditText.getText().toString();
        try {
            boolean result = mAidlInterface.addBook(new Book(bookName, bookId));
            if (result) {
                Toast.makeText(getApplicationContext(), "addBook成功！！！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "addBook失败！！！", Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            Toast.makeText(getApplicationContext(), "addBook失败！！！", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void queryBookInner() {
        if (!mIsBound || mAidlInterface == null) {
            Toast.makeText(getApplicationContext(), "请先bindService", Toast.LENGTH_SHORT).show();
            return;
        }
        String bookIdStr = mQueryBookEditText.getText().toString();
        int bookId;
        try {
            bookId = Integer.valueOf(bookIdStr);
        } catch (NumberFormatException e) {
            bookId = 0;
        }
        try {
            Book book = mAidlInterface.queryBook(bookId);
            if (book == null) {
                Toast.makeText(getApplicationContext(), "queryBook失败！！！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "查询到id 为 " + bookId + " 的图书，书名为： " + book.mBookName, Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            Toast.makeText(getApplicationContext(), "queryBook失败！！！", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return;
    }
}