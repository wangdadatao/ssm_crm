package com.datao.pojo;

import java.io.Serializable;

/**
 * Created by 海涛 on 2016/4/28.
 */
public class Log implements Serializable {

    private Integer id;
    private Integer userid;
    private String logip;
    private String logtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getLogip() {
        return logip;
    }

    public void setLogip(String logip) {
        this.logip = logip;
    }

    public String getLogtime() {
        return logtime;
    }

    public void setLogtime(String logtime) {
        this.logtime = logtime;
    }
}
