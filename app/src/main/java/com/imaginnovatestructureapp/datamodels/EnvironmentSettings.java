package com.imaginnovatestructureapp.datamodels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by bhagya on 14/06/2016.
 */
public class EnvironmentSettings implements Serializable {
    @SerializedName("protocall")
    private String protocall;
    @SerializedName("base_url")
    private String baseUrl;
    @SerializedName("crittercism_api_key")
    private String crittercismApiKey;
    @SerializedName("analytics_tracking_id")
    private String analytics_tracking_id;
    @SerializedName("parse_application_id")
    private String parse_application_id;
    @SerializedName("parse_client_key")
    private String parse_client_key;

    public String getProtocall() {
        return protocall;
    }

    public void setProtocall(String protocall) {
        this.protocall = protocall;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getCrittercismApiKey() {
        return crittercismApiKey;
    }

    public void setCrittercismApiKey(String crittercismApiKey) {
        this.crittercismApiKey = crittercismApiKey;
    }

    public String getAnalytics_tracking_id() {
        return analytics_tracking_id;
    }

    public void setAnalytics_tracking_id(String analytics_tracking_id) {
        this.analytics_tracking_id = analytics_tracking_id;
    }

    public String getParse_application_id() {
        return parse_application_id;
    }

    public void setParse_application_id(String parse_application_id) {
        this.parse_application_id = parse_application_id;
    }

    public String getParse_client_key() {
        return parse_client_key;
    }

    public void setParse_client_key(String parse_client_key) {
        this.parse_client_key = parse_client_key;
    }
}
