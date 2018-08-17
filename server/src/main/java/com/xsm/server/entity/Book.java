package com.xsm.server.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: 夏胜明
 * Date: 2018/8/17 0017
 * Email: xiasem@163.com
 * Description:
 */
public class Book implements Parcelable{
    private String name;
    private String price;

    public Book() {
    }

    protected Book(Parcel in) {
        name = in.readString();
        price = in.readString();
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
    }
}

