package com.imaginnovatestructureapp.Controllers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.VolleyError;
import com.imaginnovatestructureapp.Listeners.AccountRegistrationListener;
import com.imaginnovatestructureapp.datamodels.BaseResponseInterface;
import com.imaginnovatestructureapp.datamodels.data.BaseDataInterface;
import com.imaginnovatestructureapp.datamodels.data.ErrorMsg;
import com.imaginnovatestructureapp.datamodels.data.Message;
import com.imaginnovatestructureapp.network.webservices.AccountRegistrationWS;
import com.imaginnovatestructureapp.network.webservices.ResultListener;
import com.imaginnovatestructureapp.network.webservices.ServiceConstants;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Abhishek on 1/27/2015.
 */
public class AccountRegistrationController implements ResultListener {

    AccountRegistrationListener accountRegistrationListener;
    Bundle accountdata;
    HashMap<Integer, Integer> hm_type_result;
    private Bundle data;


    public AccountRegistrationController(AccountRegistrationListener listener) {
        this.accountRegistrationListener = listener;
        hm_type_result = new HashMap<Integer, Integer>();
    }
    public void sendRegisterRequest(Bundle bundle) {
        // send confirm organization user service
        sendToServer(data);
    }

    public void sendToServer(Bundle data) {
        accountdata = data;
        AccountRegistrationWS accountRegistrationWS = new AccountRegistrationWS(ServiceConstants.ACC_REGISTRATION, this);

        accountRegistrationWS.sendRequestToServer(data);
    }

    public Activity getContext() {
        return accountRegistrationListener.getContext();
    }

    @Override
    public synchronized void onNWServiceResultReceived(BaseResponseInterface result, VolleyError error, int reqId) {

        if (result != null) {

            if (reqId == ServiceConstants.ACC_REGISTRATION ) {

                BaseResponseInterface bri = (BaseResponseInterface) result;

                if (bri.getData() != null) {

                    ArrayList<? extends BaseDataInterface> al = bri.getData();
                    ArrayList<Message> msg_data = new ArrayList<Message>();
                    ArrayList<ErrorMsg> err_data = new ArrayList<ErrorMsg>();

                    Bundle otherinfo = bri.getOtherInfo();

                    if (otherinfo != null) {
                        //get user info etc
                    }


                    if (al != null && al.size() > 0) {
                        for (BaseDataInterface bdi : al) {
                            if (bdi instanceof Message) {
                                Message m = (Message) bdi;
                                msg_data.add(m);
                            } else if (bdi instanceof ErrorMsg) {
                                ErrorMsg emsg = (ErrorMsg) bdi;
                                err_data.add(emsg);
                            }
                        }
                    }

                    if (bri.isSuccess()) {
                        accountRegistrationListener.setResultOnSuccess(reqId, msg_data);
                        hm_type_result.put(reqId, ServiceConstants.SUCCESS);
                        // SAVE TO DB
                    } else {
                        accountRegistrationListener.setResultOnFailure(reqId, err_data);
                        hm_type_result.put(reqId, ServiceConstants.FAILURE);
                    }

                }

            }

        } else if (error != null && error instanceof VolleyError) {

            if (reqId == ServiceConstants.ACC_REGISTRATION ) {

                VolleyError volleyError = (VolleyError) error;

                Log.d("FLOW ERR:-> ", error.toString());

                hm_type_result.put(reqId, ServiceConstants.ERROR);

                if (volleyError.networkResponse == null) {

                    accountRegistrationListener.setReasonOnError(reqId, "No Connection" + "");

                } else {
                    accountRegistrationListener.setReasonOnError(reqId, error.networkResponse.statusCode + "");
                }

            }

        }

        accountRegistrationListener.onResultReceived(reqId, hm_type_result.get(reqId));

        Log.d("FLOW", "NW RESULT RECEIVED " + reqId);

    }

    @Override
    public synchronized void onDBServiceResultReceived(int code, Bundle data) {

        Log.d("FLOW", "DB RESULT RECEIVED: " + code);
    }

}
