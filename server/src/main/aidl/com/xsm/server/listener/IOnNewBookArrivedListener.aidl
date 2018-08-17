// IOnNewBookArrivedListener.aidl
package com.xsm.server.listener;
import com.xsm.server.entity.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
