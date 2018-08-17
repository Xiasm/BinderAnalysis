// IRemoteService.aidl
package com.xsm.server;
import com.xsm.server.entity.Book;
import com.xsm.server.listener.IOnNewBookArrivedListener;

interface IRemoteService {

   void addBook(in Book book);

   List<Book> getBookList();

   void registerListener(IOnNewBookArrivedListener listener);

   void unregisterListener(IOnNewBookArrivedListener listener);

}

