package com.imaginnovatestructureapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.imaginnovatestructureapp.datamodels.EnvironmentSettings;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Santosh on 5/30/2016.
 */
public class AppApplication extends Application {

    public static final int PROD = 0;
    public static final int STAGE = 1;
    public static final int TEST = 2;
    public static int env = PROD;
    public static String environment;
    private static EnvironmentSettings envSettings;
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        loadEnvironmentValues();
    }

    private void loadEnvironmentValues() {
        Log.v("loadEnvironmentValues", "loadEnvironmentValues=");
        try {
            String settingsFile = null;
            switch (env) {
                case PROD:
                    settingsFile = "app_settings_prod.json";
                    environment = "prod";
                    break;
                case STAGE:
                    settingsFile = "app_settings_stage.json";
                    environment = "stage";
                    break;
                case TEST:
                    settingsFile = "app_settings_test.json";
                    environment = "test";
                    break;
            }
            InputStream inputStream = this.getAssets().open(settingsFile);
            JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream));
            Gson gson = new Gson();
            envSettings = gson.fromJson(jsonReader, EnvironmentSettings.class);

        } catch (IOException e) {
            Log.d("EnvironmentSettings", "Error loading env values", e);
        }
    }
    public static EnvironmentSettings getEnvSettings() {
        return envSettings;
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static void setAppContext(Context appContext) {
        AppApplication.appContext = appContext;
    }
}
