package com.xsm.binderanalysis;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xsm.server.IRemoteService;
import com.xsm.server.entity.Book;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ServiceConnection serviceConnection =  new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRemoteService = IRemoteService.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private IRemoteService mRemoteService;
    private TextView tvText;
    private EditText edttext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Intent service = new Intent();
        service.setAction("com.xsm.server.RemoteService");
        service.setComponent(new ComponentName("com.xsm.server", "com.xsm.server.RemoteService"));
        bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    private void initView() {
        edttext = findViewById(R.id.edittext);
        findViewById(R.id.btnAddBook).setOnClickListener(this);
        findViewById(R.id.btnShowBookList).setOnClickListener(this);
        tvText = findViewById(R.id.tv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddBook:
                addBook();
                break;
            case R.id.btnShowBookList:
                getBookList();
                break;
        }
    }

    private void getBookList() {
        if (mRemoteService == null) {
            Toast.makeText(this, "没有远程连接！", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            List<Book> list = mRemoteService.getBookList();
            tvText.setText(list.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    private void addBook() {
        String bookName = edttext.getText().toString();
        if (TextUtils.isEmpty(bookName)) {
            Toast.makeText(this, "图书名字为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mRemoteService == null) {
            Toast.makeText(this, "没有远程连接！", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Book book = new Book();
            book.setName(bookName);
            mRemoteService.addBook(book);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
