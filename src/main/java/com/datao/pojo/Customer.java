package com.datao.pojo;

import java.io.Serializable;

/**
 * Created by 海涛 on 2016/5/3.
 */
public class Customer implements Serializable {

    public static final String PROGRESS_NEW = "无";
    public static final String PROGRESS_PRICE = "报价";
    public static final String PROGRESS_INTENTION = "意向";
    public static final String PROGRESS_SUCCESS = "成交";
    public static final String PROGRESS_FAIL = "暂时搁置";
    public static final String PROGRESS_VISIT = "初访";


    private Integer id;
    private String company;
    private String linkman;
    private String tel;
    private String address;
    private String email;
    private String wechat;
    private String qq;
    private String mark;
    private Integer userid;
    private String progress;
    private String progresstime;
    private Integer createid;
    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getProgresstime() {
        return progresstime;
    }

    public void setProgresstime(String progresstime) {
        this.progresstime = progresstime;
    }

    public Integer getCreateid() {
        return createid;
    }

    public void setCreateid(Integer createid) {
        this.createid = createid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

}
