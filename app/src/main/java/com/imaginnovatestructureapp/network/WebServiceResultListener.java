package com.imaginnovatestructureapp.network;

/**
 * Created by bhagya on 14/06/2016.
 */
public interface WebServiceResultListener {
    public void onServiceCompleted(Object response, Object error, int reqId);
}
