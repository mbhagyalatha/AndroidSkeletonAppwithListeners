package com.imaginnovatestructureapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.imaginnovatestructureapp.Listeners.BaseResultListener;
import com.imaginnovatestructureapp.R;
import com.imaginnovatestructureapp.datamodels.data.ErrorMsg;
import com.imaginnovatestructureapp.datamodels.data.Message;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Santosh on 6/8/2016.
 */
public class BaseActivity extends AppCompatActivity implements BaseResultListener {
    public Activity context;
    public HashMap<Integer, ArrayList<Message>> hm_success_call_result;
    public HashMap<Integer, ArrayList<ErrorMsg>> hm_failure_call_result;
    public HashMap<Integer, String> hm_error_call_result;

    public BaseActivity() {
        initializeVariables();
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setResultOnSuccess(int calltype, ArrayList<Message> result) {
        hm_success_call_result.put(calltype, result);
    }

    @Override
    public void setResultOnFailure(int calltype, ArrayList<ErrorMsg> result) {
        hm_failure_call_result.put(calltype, result);
    }

    @Override
    public void setReasonOnError(int calltype, String msg) {
        hm_error_call_result.put(calltype, msg);
    }

    @Override
    public void onResultReceived(int call, int type) {

    }

    public Activity getContext() {
        return context;
    }

    public void setContext(Activity ctxt) {
        this.context = ctxt;
    }

    public void initializeVariables() {
        hm_success_call_result = new HashMap<Integer, ArrayList<Message>>();
        hm_failure_call_result = new HashMap<Integer, ArrayList<ErrorMsg>>();
        hm_error_call_result = new HashMap<Integer, String>();
    }
    public void setColorToStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }
}
