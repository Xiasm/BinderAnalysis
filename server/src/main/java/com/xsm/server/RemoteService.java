package com.xsm.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import com.xsm.server.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class RemoteService extends Service {
    private List<Book> mBookList = new ArrayList<>();

    public RemoteService() {
    }

    private Binder mBinder = new IRemoteService.Stub() {


        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

}
