package com.imaginnovatestructureapp.network.webservices;

import android.content.Context;
import android.os.Bundle;

import com.android.volley.VolleyError;
import com.imaginnovatestructureapp.datamodels.BaseResponseInterface;


/**
 * Created by bhagya on 14/06/2016.
 */
public interface ResultListener {
    public void onNWServiceResultReceived(BaseResponseInterface result, VolleyError error, int reqId);
    public void onDBServiceResultReceived(int code, Bundle data);
    public Context getContext();
}