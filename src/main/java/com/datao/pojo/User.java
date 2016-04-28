package com.datao.pojo;

import java.io.Serializable;

/**
 * Created by 海涛 on 2016/4/28.
 * User对象
 */
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String tel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
