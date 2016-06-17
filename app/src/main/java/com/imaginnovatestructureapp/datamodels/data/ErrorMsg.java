package com.imaginnovatestructureapp.datamodels.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bhagya on 14/06/2016.
 */
public class ErrorMsg implements BaseDataInterface {

    @SerializedName("code")
    private Integer code;

    @SerializedName("message")
    private String message;

    public ErrorMsg(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public ErrorMsg(){}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
