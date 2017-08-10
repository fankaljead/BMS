package com.Icode.AccountManage.controller;

import com.Icode.AccountManage.service.IBookService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * Author: Zhou Xianghui
 * Time: 2017/8/8 15:41
 * Description:书籍管理
 */
@RestController
public class BookController {
    @Resource
    IBookService service;



    /**
     * 新增书籍
     * @param bookName ：书名
     * @param bookAuthor ：作者
     * @param bookImageUrl: 书封面图片地址
     * @param bookTotalNum: 书的总数
     * @return
     */
    @RequestMapping(value="/addBook",method= RequestMethod.POST)
    @ResponseBody
    public String addBook(String bookName, String bookImageUrl, String bookAuthor, int bookTotalNum){
        return service.addBook(bookName, bookImageUrl, bookAuthor, bookTotalNum) + "";
    }


    /**
     * 删除书籍
     * @param bookName ：书名
     * @return
     */
    @RequestMapping(value="/deleteBook",method= RequestMethod.POST)
    @ResponseBody
    public String deleteBook(String bookName){
        return service.deleteBook(bookName) + "";
    }

    /**
     * 搜索书籍
     * @param bookName ：书名
     * @return
     */
    @RequestMapping(value="/searchBook",method= RequestMethod.POST)
    @ResponseBody
    public String searchBook(String bookName){

//        System.out.println(JSONObject.fromObject(service.searchBook(bookName)).toString());
        return  JSONObject.fromObject(service.searchBook(bookName)).toString();
    }

    /**
     * 更新书籍
     * @param bookName ：书名
     * @return
     */
    @RequestMapping(value="/updateBook",method= RequestMethod.POST)
    @ResponseBody
    public String updateBook(String bookName, int bookAddNum){
        return service.updateBook(bookName, bookAddNum) + "";
    }


    /**
     * 借书
     * @param bookName ：书名
     * @return
     */
    @RequestMapping(value="/borrowBook",method= RequestMethod.POST)
    @ResponseBody
    public String borrowBook(String bookName, String userName){
        return service.borrowBook(bookName, userName) + "";
    }

    /**
     * 还书
     * @param bookName ：书名
     * @return
     */
    @RequestMapping(value="/returnBook",method= RequestMethod.POST)
    @ResponseBody
    public String returnBook(String bookName, String userName){
        return service.returnBook(bookName, userName) + "";
    }


    /**
     * 呈现所有的书
     *
     * @return
     */
    @RequestMapping(value="/listBook",method= RequestMethod.POST)
    @ResponseBody
    public String listBook(){


        return JSONArray.fromObject(service.listBook()).toString();

    }

//    @RequestMapping(value="/searchBook",method= RequestMethod.POST)
//    @ResponseBody
//    public String searchBook(String bookName){
//
//        return service.searchBook(bookName).toString();
//
//    }



}
