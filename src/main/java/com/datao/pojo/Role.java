package com.datao.pojo;

import java.io.Serializable;

/**
 * Created by 海涛 on 2016/4/28.
 */
public class Role implements Serializable {

    private Integer id;
    private String rolename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String roleName) {
        this.rolename = roleName;
    }
}
