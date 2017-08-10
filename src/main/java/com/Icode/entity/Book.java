package com.Icode.entity;

/**
 * Author: Zhou Xianghui
 * Time: 2017/8/7 14:52
 * Description: Book书籍类, 存储书籍的ID, 书名, 作者, 数量, 图片位置
 */
public class Book extends Entity{

    private String ID;//书籍的ID
    private String bookName;//书名
    private String bookImageUrl;//书图片所在路径
    private String bookAuthor;//书的作者

    private int bookTotalNum;//书的总数量
    private int bookCurrentNum;//书的当前数量
    private int bookBorrowedNum;//书的被借走数量





    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookImageUrl() {
        return bookImageUrl;
    }

    public void setBookImageUrl(String bookImageUrl) {
        this.bookImageUrl = bookImageUrl;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookTotalNum() {
        return bookTotalNum;
    }

    public void setBookTotalNum(int bookTotalNum) {
        this.bookTotalNum = bookTotalNum;
    }

    public int getBookCurrentNum() {
        return bookCurrentNum;
    }

    public void setBookCurrentNum(int bookCurrentNum) {
        this.bookCurrentNum = bookCurrentNum;
    }

    public int getBookBorrowedNum() {
        return bookBorrowedNum;
    }

    public void setBookBorrowedNum(int bookBorrowedNum) {
        this.bookBorrowedNum = bookBorrowedNum;
    }



    public String getTableName() {
        return "book";
    }

    public String getPrimaryKey() {
        return "ID";
    }


}
