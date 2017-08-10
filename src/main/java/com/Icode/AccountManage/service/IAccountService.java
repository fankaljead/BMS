package com.Icode.AccountManage.service;

import com.Icode.entity.Account;
import com.Icode.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by Icode on 2017/8/3.
 * descript:账户管理
 */
public interface IAccountService {
     int login(String userName, String password, String rememberMe, String autoLogin, HttpServletResponse response,HttpServletRequest request);
     int register(String loginName,String loginPassword);
     int delete(String loginName);
     int update(String loginName, String password);
     ArrayList<Account> listAccount();
     ArrayList<User> listUser();

}
