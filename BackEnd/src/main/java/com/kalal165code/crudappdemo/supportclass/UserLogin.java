package com.kalal165code.crudappdemo.supportclass;

public class UserLogin {

    private String username;
    private String password;
    private String message;
    private int imgFlag;

    public UserLogin(){}

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getImgFlag() {
        return imgFlag;
    }

    public void setImgFlag(int imgFlag) {
        this.imgFlag = imgFlag;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", message='" + message + '\'' +
                ", imgFlag='" + imgFlag + '\'' +
                '}';
    }
}
