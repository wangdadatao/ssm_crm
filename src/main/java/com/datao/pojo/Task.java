package com.datao.pojo;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;

/**
 * Created by 海涛 on 2016/5/5.
 */
public class Task implements Serializable {

    private Integer id;
    private String task;
    private String worktime;
    private String status;
    private Integer userid;
    private Integer custid;
    private String donetime;
    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCustid() {
        return custid;
    }

    public void setCustid(Integer custid) {
        this.custid = custid;
    }

    public String getDonetime() {
        return donetime;
    }

    public void setDonetime(String donetime) {
        this.donetime = donetime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    //判断时间是否过期
    public  Boolean overTime(){
        String time = this.worktime;
        if(getStatus().equals("true")){
            return false;
        }
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dt = formatter.parseDateTime(getWorktime());
        return dt.isBeforeNow();
    }

    //得到星期几
    public String getDayAndW(){
        String[] week = {"一","二","三","四","五","六","七"};

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dt = formatter.parseDateTime(getWorktime());
        int wee = dt.getDayOfWeek();

        return dt.toString("MM月dd日 周" + week[wee-1]);
    }

    //判断是否已经完成
    public Boolean ifDone(){
        return getStatus().equals("true");
    }

}
