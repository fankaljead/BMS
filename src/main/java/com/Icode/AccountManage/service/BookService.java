package com.Icode.AccountManage.service;

import com.Icode.dao.BaseEntityDao;
import com.Icode.dao.SearchDao;
import com.Icode.entity.Book;
import com.Icode.entity.BookBorrowInfo;
import com.Icode.tools.EntityIDFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: Zhou Xianghui
 * Time: 2017/8/8 15:22
 * Description: 书籍管理逻辑处理
 */
@Service
public class BookService implements IBookService{

    @Resource(name = "baseEntityDao")
    private BaseEntityDao dao;
    @Resource(name = "searchDao")
    private SearchDao searchDao;

    /**
     * @param bookName
     * @return 通过书名获取书籍信息
     */
    private Book getBookByName(String bookName) {
        String condition = " bookName = '" + bookName + "' ";
        List<Map<String, Object>> list = dao.getByCondition(condition, "book");

        Book book = new Book();

        if (list == null || list.size() == 0) {
            return null;
        } else {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                book.setID(map.get("ID").toString());
                book.setBookName(map.get("BookName").toString());
                book.setBookImageUrl(map.get("BookImageUrl").toString());
                book.setBookTotalNum(Integer.valueOf(map.get("BookTotalNum").toString()));
                book.setBookBorrowedNum(Integer.valueOf(map.get("BookBorrowedNum").toString()));
                book.setBookAuthor(map.get("BookAuthor").toString());
                book.setBookCurrentNum(Integer.valueOf(map.get("BookCurrentNum").toString()));
            }
        }
        return book;
    }



    public int addBook(String bookName, String bookImageUrl, String bookAuthor, int bookTotalNum) {

        try{

            Book book = new Book();
            book.setID(EntityIDFactory.createId());
            book.setBookName(bookName);
            book.setBookImageUrl(bookImageUrl);
            book.setBookAuthor(bookAuthor);
            book.setBookTotalNum(bookTotalNum);
            book.setBookBorrowedNum(0);
            book.setBookCurrentNum(bookTotalNum);

            // 保存书籍信息
            dao.save(book);

            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    public int deleteBook(String bookName) {
        try{
            Book book = getBookByName(bookName);
            if(book != null){
                String condition = " BookName = '" + bookName + "' ";
                dao.deleteByCondition(condition, "book");

                return 1;//删除成功

            }
            else {
                return 0;//该书不存在

            }
        }catch (Exception e){
            return -1;//删除失败
        }
    }

    public Book searchBook(String bookName) {

        try{
            Book book = getBookByName(bookName);
            if(book == null){
                return null;//该书不存在
            }
            else {



                return book;//查找成功

            }
        }catch (Exception e){
            return null;//查找失败
        }
    }

    public int updateBook(String bookName, int bookAddNum) {
        try{
            Book book = getBookByName(bookName);

            if(book != null){

                book.setBookTotalNum(book.getBookTotalNum() + bookAddNum);
                book.setBookCurrentNum(book.getBookCurrentNum() + bookAddNum);
                dao.updatePropByID(book, book.getID());

                return 1;//成功
            }
            else
                return 0;//书不存在


        }catch (Exception e){
            
            return -1;//更改书籍信息失败
        }
    }

    public int borrowBook(String bookName, String userName) {
        try{
            Book book = getBookByName(bookName);

            if(book != null){

                book.setBookCurrentNum(book.getBookCurrentNum() - 1);
                book.setBookBorrowedNum(book.getBookBorrowedNum() + 1);
                dao.updatePropByID(book, book.getID());

                BookBorrowInfo bookBorrowInfo = new BookBorrowInfo();
                bookBorrowInfo.setID(EntityIDFactory.createId());
                bookBorrowInfo.setBookName(book.getBookName());
                bookBorrowInfo.setBorrowOrReturn((short)1);
                bookBorrowInfo.setUserName(userName);
                dao.save(bookBorrowInfo);

                return 1;//成功
            }
            else
                return 0;//借的书不存在


        }catch (Exception e){
            return -1;//借书失败
        }
    }

    public int returnBook(String bookName, String userName) {
        try{
            Book book = getBookByName(bookName);

            if(book != null){

                book.setBookCurrentNum(book.getBookCurrentNum() + 1);
                book.setBookBorrowedNum(book.getBookBorrowedNum() - 1);
                dao.updatePropByID(book, book.getID());


                BookBorrowInfo bookBorrowInfo = new BookBorrowInfo();
                bookBorrowInfo.setID(EntityIDFactory.createId());
                bookBorrowInfo.setBookName(book.getBookName());
                bookBorrowInfo.setBorrowOrReturn((short)0);
                bookBorrowInfo.setUserName(userName);
                dao.save(bookBorrowInfo);

                return 1;//成功
            }
            else
                return 0;//还的书不存在


        }catch (Exception e){
            return -1;//还书失败
        }
    }

    public ArrayList<Book> listBook() {

        //String condition = " BookName = '" + "red" + "' ";
        List<Map<String, Object>> list = dao.getByCondition(null, "book");




        ArrayList<Book> b = new ArrayList<Book>();
        if (list == null || list.size() == 0) {

            return null;
        } else {
            for (int i = 0; i < list.size(); i++) {

                Book book = new Book();
                Map<String, Object> map = list.get(i);
                book.setID(map.get("ID").toString());
                book.setBookName(map.get("BookName").toString());
                book.setBookTotalNum(Integer.valueOf(map.get("BookTotalNum").toString()));
                book.setBookAuthor(map.get("BookAuthor").toString());
                book.setBookImageUrl(map.get("BookImageUrl").toString());
                book.setBookBorrowedNum(Integer.valueOf(map.get("BookBorrowedNum").toString()));
                book.setBookCurrentNum(Integer.valueOf(map.get("BookCurrentNum").toString()));

                b.add(book);

            }

            return b;
        }
    }



}
