package com.ndf.demo.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lin on 4/20/18.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String userName;

    private String password;

    private int status;

    private Date createTime;

    public User() {
    }

    public User(String userName, String password, int status) {
        this.userName = userName;
        this.password = password;
        this.status = status;
        this.createTime = new Date();
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }

}
