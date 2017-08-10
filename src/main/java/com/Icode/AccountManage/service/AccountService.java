package com.Icode.AccountManage.service;

import com.Icode.dao.BaseEntityDao;
import com.Icode.dao.SearchDao;
import com.Icode.entity.Account;
import com.Icode.entity.User;
import com.Icode.tools.EntityIDFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Icode on 2017/8/3.
 * descript:账户管理逻辑处理
 */
@Service
public class AccountService implements IAccountService {
    @Resource(name = "baseEntityDao")
    private BaseEntityDao dao;
    @Resource(name = "searchDao")
    private SearchDao searchDao;
    /**
     * @param loginName
     * @return 通过登录名获取账户信息
     */
    private Account getUserByName(String loginName) {
        String condition = " loginName = '" + loginName + "' ";
        List<Map<String, Object>> list = dao.getByCondition(condition, "account");

        Account account = new Account();

        if (list == null || list.size() == 0) {
            return null;
        } else {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                account.setID(map.get("ID").toString());
                account.setLoginName(loginName);
                account.setLoginPassword(map.get("LoginPassword").toString());
                account.setAutoLogin(Short.parseShort(map.get("AutoLogin").toString()));
                account.setRememberMe(Short.parseShort(map.get("RememberMe").toString()));
                account.setUserID(map.get("UserID").toString());
            }
        }
        return account;
    }

    /**
     * @param userName
     * @param password
     * @param rememberMe
     * @param autoLogin
     * @return 是否成功登陆，1：成功  0:用户不存在  -1：密码错误
     */
    public int login(String userName, String password, String rememberMe, String autoLogin,HttpServletResponse response,HttpServletRequest request) {
        Account account = getUserByName(userName);
        if (account == null) {
            return 0;
        } else if (password.equals(account.getLoginPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("ACCOUNTID",account.getID());
            session.setAttribute("USERID",account.getUserID());
            session.setAttribute("LOGINNAME",account.getLoginName());
            if ("on".equals(rememberMe)) {
                //创建两个Cookie对象
                Cookie nameCookie = new Cookie("userName", userName);
                //设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 30);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 30);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            return 1;
        } else {
            return -1;
        }
    }

    /**
     *
     * @param loginName
     * @param loginPassword
     * @return 1:成功 -1：失败
     */
    public int register(String loginName, String loginPassword) {
        try{
            Account account = new Account();
            account.setID(EntityIDFactory.createId());
            account.setLoginName(loginName);
            account.setLoginPassword(loginPassword);
            account.setRememberMe((short) 0);
            account.setAutoLogin((short) 0);

            User user = new User();
            user.setID(EntityIDFactory.createId());

            account.setUserID(user.getID());
            // 保存账户信息
            dao.save(account);
            // 保存用户信息
            dao.save(user);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    /**
     *
     * @param loginName
     *
     * @return 是否成功删除 1:成功 0: 用户不存在, 失败 -1：失败
     */
    public int delete(String loginName) {
        try{
            Account account = getUserByName(loginName);
            if(account == null){
                return 0;//用户不存在
            }
            else {

                dao.deleteByID(account.getUserID(), "user", "ID");
                dao.deleteByID(account.getID(), account.getTableName(), "ID");
                return 1;//删除成功

            }
        }catch (Exception e){
            return -1;//删除失败
        }

    }

    /**
     *
     * @param loginName
     * @param password
     * @return 是否成功修改密码 1:成功 0: 用户不存在, 失败 -1：失败
     */
    public int update(String loginName, String password) {

        try{
            Account account = getUserByName(loginName);

            if(account != null){

                account.setLoginPassword(password);
                dao.updatePropByID(account, account.getID());

                return 1;//成功
            }
            else
                return 0;//用户不存在


        }catch (Exception e){
            return -1;//更改密码失败
        }

    }

    /**
     * 查看所有account信息
     * @return
     */
    public ArrayList<Account> listAccount() {

        List<Map<String, Object>> list = dao.getByCondition(null, "account");
        ArrayList<Account> a = new ArrayList<Account>();

        if (list == null || list.size() == 0) {

            return null;
        } else {
            for (int i = 0; i < list.size(); i++) {

                Account account = new Account();
                Map<String, Object> map = list.get(i);
                account.setID(map.get("ID").toString());
                account.setLoginPassword(map.get("LoginPassword").toString());
                account.setLoginName(map.get("LoginName").toString());
                account.setUserID(map.get("UserID").toString());
                account.setAutoLogin(Short.valueOf(map.get("AutoLogin").toString()));
                account.setRememberMe(Short.valueOf(map.get("RememberMe").toString()));

                a.add(account);

            }

            return a;
        }
    }


    /**
     * 查看所有用户信息
     * @return
     */
    public ArrayList<User> listUser() {

        List<Map<String, Object>> list = dao.getByCondition(null, "user");
        ArrayList<User> u = new ArrayList<User>();

        if (list == null || list.size() == 0) {

            return null;
        } else {
            for (int i = 0; i < list.size(); i++) {

                User user = new User();
                Map<String, Object> map = list.get(i);
                user.setID(map.get("ID").toString());
                user.setName(map.get("Name").toString());
                user.setAge(Integer.valueOf(map.get("Age").toString()));
                user.setSex(Short.valueOf(map.get("Sex").toString()));
                user.setPhoneNumber(map.get("PhoneNumber").toString());

                u.add(user);

            }

            return u;
        }
    }
}
