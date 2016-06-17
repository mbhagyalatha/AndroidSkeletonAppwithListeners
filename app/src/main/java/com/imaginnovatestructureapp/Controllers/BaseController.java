package com.imaginnovatestructureapp.Controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.VolleyError;
import com.imaginnovatestructureapp.Listeners.BaseResultListener;
import com.imaginnovatestructureapp.datamodels.BaseResponseInterface;
import com.imaginnovatestructureapp.network.webservices.ResultListener;

import java.util.HashMap;

/**
 * Created by Developer on 2/17/2015.
 */
public class BaseController implements ResultListener {

    public BaseResultListener msmResultListener;

    public Intent regDBService;
    public Bundle data;
    public Bundle other_info;
    public HashMap<Integer, Integer> hm_type_result;


    BaseController(BaseResultListener listener) {
        msmResultListener = listener;
        hm_type_result = new HashMap<Integer, Integer>();
    }

    public Bundle getOther_info() {
        return other_info;
    }

    public void setOther_info(Bundle other_info) {
        this.other_info = other_info;
    }

    @Override
    public void onNWServiceResultReceived(BaseResponseInterface result, VolleyError error, int reqId) {

        msmResultListener.onResultReceived(reqId, hm_type_result.get(reqId));

        Log.d("FLOW", "NW RESULT RECEIVED " + reqId);
    }

    @Override
    public Activity getContext() {
        return msmResultListener.getContext();
    }

    @Override
    public void onDBServiceResultReceived(int code, Bundle data) {
        Log.d("FLOW", "DB RESULT RECEIVED: " + code);
    }


}
