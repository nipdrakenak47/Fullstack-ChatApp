package com.kalal165code.crudappdemo.entity;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name = "user")
public class User {

    // Define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "imgflag")
    private int imgFlag;

    @Lob
    @Column(name = "filedata")
    private byte[] fileData;

    // Define constructors
    public User(){}

    public User(String userName, String password, int imgFlag,byte[] fileData) {
        this.userName = userName;
        this.password = password;
        this.imgFlag = imgFlag;
        this.fileData = fileData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getImgFlag() {
        return imgFlag;
    }

    public void setImgFlag(int imgFlag) {
        this.imgFlag = imgFlag;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", imgFlag='" + imgFlag + '\'' +
                ", fileData Size=" + fileData.length +
                '}';
    }
}
