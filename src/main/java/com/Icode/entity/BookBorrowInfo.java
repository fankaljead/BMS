package com.Icode.entity;

/**
 * Author: Zhou Xianghui
 * Time: 2017/8/8 11:18
 * Description:
 */
public class BookBorrowInfo extends Entity{

    private String ID;//借还书的编号
    private String bookName;//被借的书的名字
    private String userName;//借书人名字
    private short borrowOrReturn;//书是借还是还 1表示借, 0表示还


    public String getID() { return ID; }

    public void setID(String ID) {
        this.ID = ID;
    }



    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public short getBorrowOrReturn() {
        return borrowOrReturn;
    }

    public void setBorrowOrReturn(short borrowOrReturn) {
        this.borrowOrReturn = borrowOrReturn;
    }

    public String getTableName() {
        return "bookBorrowInfo";
    }

    public String getPrimaryKey() {
        return "ID";
    }
}
