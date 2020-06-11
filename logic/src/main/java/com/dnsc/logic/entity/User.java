package com.dnsc.logic.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "loginname")
    private String loginName;
    @Column(name = "password")
    private String passWord;
    @Column(name = "username")
    private String userName;
    @Column(name = "sex")
    private String sex;
    @Column(name = "gid")
    private int gid;
    @Column(name = "cid")
    private String cid;
    @Column(name = "userid")
    private int userId;

    public User() {
        super();
    }

    public User(String loginName, String passWord, String userName, String sex, int gid, String cid, int userId) {
        super();
        this.loginName = loginName;
        this.passWord = passWord;
        this.userName = userName;
        this.sex = sex;
        this.gid = gid;
        this.cid = cid;
        this.userId = userId;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getLoginName(){
        return loginName;
    }
    public void setLoginName(String loginName){
        this.loginName = loginName;
    }
    public String getPassWord(){
        return passWord;
    }
    public void setPassWord(String passWord){
        this.passWord = passWord;
    }

    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getSex(){
        return sex;
    }
    public void setSex(String sex){
        this.sex = sex;
    }

    public int getGid(){
        return gid;
    }
    public void setGid(int gid){
        this.gid = gid;
    }

    public String getCid(){
        return cid;
    }
    public void setCid(String cid){ this.passWord = cid; }

    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }

}
