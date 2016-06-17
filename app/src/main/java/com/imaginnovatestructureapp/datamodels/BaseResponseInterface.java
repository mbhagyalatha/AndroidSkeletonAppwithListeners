package com.imaginnovatestructureapp.datamodels;

import android.os.Bundle;

import com.imaginnovatestructureapp.datamodels.data.BaseDataInterface;

import java.util.ArrayList;

/**
 * Created by bhagya on 14/06/2016.
 */
public interface BaseResponseInterface {

    public boolean isSuccess();
    public ArrayList<? extends BaseDataInterface> getData();
    public Bundle getOtherInfo();


}
