package com.Icode.entity;

/**
 * Created by Icode on 2017/7/29.
 * descript:登录账号
 */
public class Account extends Entity{
    private String ID;
    private String LoginName;
    private String LoginPassword;
    private String UserID;
    private short RememberMe;
    private short AutoLogin;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getLoginPassword() {
        return LoginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        LoginPassword = loginPassword;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public short getRememberMe() {
        return RememberMe;
    }

    public void setRememberMe(short rememberMe) {
        RememberMe = rememberMe;
    }

    public short getAutoLogin() {
        return AutoLogin;
    }

    public void setAutoLogin(short autoLogin) {
        AutoLogin = autoLogin;
    }

    public String getTableName() {
        return "account";
    }

    public String getPrimaryKey() {
        return "ID";
    }
}
