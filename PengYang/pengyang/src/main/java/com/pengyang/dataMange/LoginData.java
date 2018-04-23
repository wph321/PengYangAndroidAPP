package com.pengyang.dataMange;

/**
 * Created by admin on 2018/3/28.
 */

public class LoginData {

    private String username;
    private String password;
    private int loginDate;

    public LoginData() {
        super();
    }

    public LoginData(String username, String password, int loginDate) {
        this.username = username;
        this.password = password;
        this.loginDate = loginDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(int loginDate) {
        this.loginDate = loginDate;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loginDate=" + loginDate +
                '}';
    }
}
