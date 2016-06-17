package com.imaginnovatestructureapp.datamodels.ResponseModels;

import android.os.Bundle;

import com.google.gson.annotations.SerializedName;
import com.imaginnovatestructureapp.datamodels.BaseResponseInterface;
import com.imaginnovatestructureapp.datamodels.data.BaseDataInterface;
import com.imaginnovatestructureapp.datamodels.data.ErrorMsg;
import com.imaginnovatestructureapp.datamodels.data.Message;


import java.util.ArrayList;

/**
 * Created by Developer on 14/06/2016.
 */
public abstract class BaseResponse implements BaseResponseInterface {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("errors")
    private ArrayList<ErrorMsg> errors;

    @SerializedName("messages")
    private ArrayList<Message> messages;


    protected BaseResponse(Boolean success, ArrayList<ErrorMsg> errors, ArrayList<Message> messages) {
        this.success = success;
        this.errors = errors;
        this.messages = messages;
    }



    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ArrayList<ErrorMsg> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<ErrorMsg> errors) {
        this.errors = errors;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }


    @Override
    public boolean isSuccess() {
        return getSuccess();
    }

    @Override
    public abstract ArrayList<? extends BaseDataInterface> getData();

    @Override
    public abstract Bundle getOtherInfo();


}
