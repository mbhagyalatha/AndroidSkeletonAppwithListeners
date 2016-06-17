package com.imaginnovatestructureapp.network.webservices;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.imaginnovatestructureapp.AppApplication;
import com.imaginnovatestructureapp.R;
import com.imaginnovatestructureapp.datamodels.BaseResponseInterface;
import com.imaginnovatestructureapp.datamodels.ResponseModels.AccountRegistrationReponse;
import com.imaginnovatestructureapp.utils.JSONDateDeserializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by bhagya on 14/06/2016.
 */
public abstract class BaseMsmWsImpl {

    protected static RequestQueue queue = null;
    protected Gson gson;

    public enum request_type {GET, POST, PUT}

    ;

    public int getReqId() {
        return reqId;
    }

    private int reqId;
    public Context context;
    private List<ResultListener> requestListeners;
    private static final String TAG = "BaseMsmWsImpl";


    public BaseMsmWsImpl(int reqId, ResultListener listener) {
        context = listener.getContext();

        if (queue == null) {
            queue = Volley.newRequestQueue(AppApplication.getAppContext());
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JSONDateDeserializer());
        gson = gsonBuilder.create();
        requestListeners = new ArrayList<ResultListener>();
        this.reqId = reqId;
        this.addWsRequestListner(listener);

    }


    public void printRequestObject(JSONObject params) {
        Log.d("FLOW", params.toString());
    }


    public void sendRequest(request_type rt, Bundle data) {
        String url = null;
        JSONObject params = null;
        int type = 0;

        HashMap<String, Object> hmreqParams = getRequestHashMap(data);


        if (rt == request_type.POST) {

            url = getUrl(hmreqParams);
            params = new JSONObject(hmreqParams);
            type = Request.Method.POST;

            printRequestObject(params);
            Log.d("FLOW", "POST URL" + url);

        } else if (rt == request_type.GET) {

            StringBuffer sb = new StringBuffer();

            sb.append(getUrl(hmreqParams) + "?");

            type = Request.Method.GET;

            if (hmreqParams.size() > 0) {
                Set keys = hmreqParams.keySet();
                Iterator<String> iterator = keys.iterator();

                while (iterator.hasNext()) {
                    String key = iterator.next();
                    sb.append(key + "=" + hmreqParams.get(key) + "&");
                }
            }

            url = sb.deleteCharAt(sb.toString().length() - 1).toString();
            Log.d("FLOW", "GET URL" + url);

        } else if (rt == request_type.PUT) {

            url = getUrl(hmreqParams);
            params = new JSONObject(hmreqParams);
            type = Request.Method.PUT;

        }

        Log.d("FLOW", "TYPE: " + type);
        Log.d("FLOW", "URL: " + url);


        JsonObjectRequest ph_regReq = null;


        ph_regReq = new JsonObjectRequest(type, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("FLOW_RESPONSE", response.toString());
                        parseResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("FLOW ERROR", error.toString());
                sendError(error);
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return setHeaders();
            }


        };

        ph_regReq.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(ph_regReq);

    }


    protected void parseResponse(Object response) {

        JsonReader jsonReader = new JsonReader(new InputStreamReader(new ByteArrayInputStream(response.toString().getBytes())));
        BaseResponseInterface result = (BaseResponseInterface) gson.fromJson(jsonReader, getType());
        sendResponse(result);

    }

    public HashMap<String, Object> getRequestHashMap(Bundle data) {

        HashMap<String, Object> reqParams = new HashMap<String, Object>();
        Iterator<String> iterator = null;
        Set<String> keys;

        if (data != null) {
            keys = data.keySet();
            iterator = keys.iterator();
        }

        return reqParams;

    }


    public String getUrl(HashMap<String, Object> hm_req) {
        String url = "";
        String version = context.getString(R.string.version1);
        switch (reqId) {
            case ServiceConstants.ACC_REGISTRATION:
                url = WsUrlConstants.getAccountRegistrationUrl().replace("ver", version);
                break;
            case ServiceConstants.LOGIN:
                url = WsUrlConstants.getLoginUrl().replace("/ver/", "/" + version + "/");
                break;
        }
        Log.d(TAG, "Url " + url);
        return url;
    }

    public Type getType() {
        Type dataListType = null;
        switch (reqId) {
            case ServiceConstants.ACC_REGISTRATION:
                dataListType = new TypeToken<AccountRegistrationReponse>() {
                }.getType();
                break;
            case ServiceConstants.LOGIN:
                //same as above
                break;

        }
        return dataListType;
    }


    public List<ResultListener> getRequestListeners() {
        return requestListeners;
    }

    private void addWsRequestListner(ResultListener listener) {
        if (listener != null) {
            requestListeners.add(listener);
        }
    }

    protected void removeWsRequestListener(ResultListener listener) {
        if (listener != null) {
            requestListeners.add(listener);
        }
    }

    protected void sendError(VolleyError error) {
        for (ResultListener listener : getRequestListeners()) {
            if (listener != null) {
                listener.onNWServiceResultReceived(null, error, getReqId());
            }
        }
    }

    protected void sendResponse(BaseResponseInterface result) {
        for (ResultListener listener : getRequestListeners()) {
            if (listener != null) {
                listener.onNWServiceResultReceived(result, null, getReqId());
            }
        }
    }

    public Map<String, String> setHeaders() {

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("api-key", "MevVHxhd5bNkYpVjZJ9QrjuHg627m9IdUp8SL45Dw");
        headers.put("auth-token", "Auth token");//authtoken saved in shared preferences
        return headers;

    }

    public String onErrorHandle(VolleyError error) {
        String json = null;
        NetworkResponse response = error.networkResponse;
        if (response != null && response.data != null) {
            switch (response.statusCode) {
                default:
                    json = new String(response.data);
                    json = trimMessage(json, "message");
                    break;
            }
        }
        return json;
    }

    public String trimMessage(String json, String key) {
        String trimmedString = null;

        try {

            JSONObject obj = new JSONObject(json);

            trimmedString = obj.getString(key);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return trimmedString;
    }


}
