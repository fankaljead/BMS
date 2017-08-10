package com.Icode.AccountManage.service;

import com.Icode.dao.BaseEntityDao;
import com.Icode.dao.SearchDao;
import com.Icode.entity.BookBorrowInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author: Zhou Xianghui
 * Time: 2017/8/9 16:36
 * Description:
 */
@Service
public class BookBorrowedInfoService implements IBookBorrowedInfoService{

    @Resource(name = "baseEntityDao")
    private BaseEntityDao dao;
    @Resource(name = "searchDao")
    private SearchDao searchDao;

    public String searchBookBorrowedInfoByBookName(String bookName) {
        String condition = " bookName = '" + bookName + "' ";
        dao.getByCondition(condition, "bookBorrowedInfo");
        return null;
    }

    public String searchBookBorrowedInfByAccountName(String loginName) {
        return null;
    }

    public String searchBookBorrowedInfo(String bookName, String loginName) {
        return null;
    }
}
