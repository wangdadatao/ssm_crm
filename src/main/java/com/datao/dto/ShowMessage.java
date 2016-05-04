package com.datao.dto;

/**
 * Created by 海涛 on 2016/4/29.
 */
public class ShowMessage {

    public static final String SUCCESS = "alert-success";
    public static final String INFO = "alert-info";
    public static final String ERROR = "alert-error";
    public static final String DANGER = "alert-danger";


    private String status;
    private String message;

    public ShowMessage() {
    }

    public ShowMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
