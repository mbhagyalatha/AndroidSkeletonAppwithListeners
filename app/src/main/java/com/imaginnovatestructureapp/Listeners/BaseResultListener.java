package com.imaginnovatestructureapp.Listeners;

import android.app.Activity;

import com.imaginnovatestructureapp.datamodels.data.ErrorMsg;
import com.imaginnovatestructureapp.datamodels.data.Message;

import java.util.ArrayList;

/**
 * Created by bhagya on 14/06/2016.
 */
public interface BaseResultListener {

    public void setResultOnSuccess(int calltype, ArrayList<Message> result);
    public void setResultOnFailure(int calltype, ArrayList<ErrorMsg> result);
    public void setReasonOnError(int calltype, String msg);
    public void onResultReceived(int call, int type);     // S, F, E

    public Activity getContext();
}
