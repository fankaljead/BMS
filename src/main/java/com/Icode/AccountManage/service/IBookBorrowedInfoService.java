package com.Icode.AccountManage.service;

/**
 * Author: Zhou Xianghui
 * Time: 2017/8/9 16:28
 * Description:对借还书记录进行管理
 */
public interface IBookBorrowedInfoService {

    String searchBookBorrowedInfoByBookName(String bookName);
    String searchBookBorrowedInfByAccountName(String loginName);
    String searchBookBorrowedInfo(String bookName, String loginName);

}
