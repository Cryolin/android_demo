package com.colin.aidl;

import com.colin.aidl.Book;

interface IRemoteInterface {
    String getName();

    Book queryBook(int id);

    boolean addBook(in Book book);
}