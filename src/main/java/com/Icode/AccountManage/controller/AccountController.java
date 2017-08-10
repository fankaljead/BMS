package com.Icode.AccountManage.controller;

import com.Icode.AccountManage.service.IAccountService;
import net.sf.json.JSONArray;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Icode on 2017/8/3.
 * descript:账户管理
 */
@RestController
public class AccountController {
    @Resource
    IAccountService service;

    /**
     * 登录
     * @param userName ：用户名
     * @param password ：密码
     * @param rememberMe ：记住密码
     * @param autoLogin ：自动登录
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public String login(String userName, String password, String rememberMe, String autoLogin, HttpServletResponse response, HttpServletRequest request){
        return service.login(userName,password,rememberMe,autoLogin,response,request)+"";
    }

    /**
     * 注册
     * @param loginName ：登录名
     * @param loginPassword ：密码
     * @return
     */
    @RequestMapping(value="/register",method=RequestMethod.POST)
    @ResponseBody
    public String register(String loginName,String loginPassword){
        return service.register(loginName,loginPassword) + "";
    }


    /**
     * 删除
     * @param loginName ：登录名
     *
     * @return
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public String delete(String loginName){
        return service.delete(loginName) + "";
    }


    /**
     * 修改密码
     * @param loginName ：登录名
     * @param password ：密码
     * @return
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    @ResponseBody
    public String update(String loginName, String password){
        return service.update(loginName, password) + "";
    }


    /**
     * 呈现所有的account
     *
     * @return
     */
    @RequestMapping(value="/listAccount",method= RequestMethod.POST)
    @ResponseBody
    public String listAccount(){
        System.out.println(JSONArray.fromObject(service.listAccount()).toString());
        return JSONArray.fromObject(service.listAccount()).toString();
    }

    /**
     * 呈现所有的user
     *
     * @return
     */
    @RequestMapping(value="/listUser",method= RequestMethod.POST)
    @ResponseBody
    public String listUser(){
        return JSONArray.fromObject(service.listUser()).toString();
    }

}
