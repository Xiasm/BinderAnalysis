// IRemoteService.aidl
package com.xsm.server;
import com.xsm.server.entity.Book;
// Declare any non-default types here with import statements

interface IRemoteService {

   void addBook(in Book book);

   List<Book> getBookList();

}
