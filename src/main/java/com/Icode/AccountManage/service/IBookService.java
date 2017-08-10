package com.Icode.AccountManage.service;

import com.Icode.entity.Book;

import java.util.ArrayList;

/**
 * Author: Zhou Xianghui
 * Time: 2017/8/8 14:47
 * Description:对书籍进行管理
 */
public interface IBookService {
    int addBook(String bookName, String bookImageUrl, String bookAuthor, int bookTotalNum);
    int deleteBook(String bookName);
    Book searchBook(String bookName);
    int updateBook(String bookName, int bookAddNum);
    int borrowBook(String bookName, String userName);
    int returnBook(String bookName, String userName);
    ArrayList<Book> listBook();

}
