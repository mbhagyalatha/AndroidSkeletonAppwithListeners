package com.imaginnovatestructureapp.network.webservices;

import android.app.Application;
import android.util.Log;

import com.imaginnovatestructureapp.AppApplication;

/**
 * Created by bhagya on 14/06/2016.
 */
public class WsUrlConstants {
    private static String EP_ACCOUNT_REGISTRATION = "/api/ver/register";
    private static String EP_LOGIN = "/api/ver/login";

    public static String getAccountRegistrationUrl() {
        return getUrl(EP_ACCOUNT_REGISTRATION);
    }

    public static String getLoginUrl() {
        return getUrl(EP_LOGIN);
    }

    public static String getUrl(String endPoint) {
        String url = null;
        String baseUrl = AppApplication.getEnvSettings().getBaseUrl();
        url = baseUrl + "v1" + endPoint;
        Log.d("service url", url);
        return url;
    }
}
