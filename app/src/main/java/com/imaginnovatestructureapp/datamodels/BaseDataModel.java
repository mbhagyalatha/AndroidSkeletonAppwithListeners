package com.imaginnovatestructureapp.datamodels;

import java.io.Serializable;

/**
 * Created by bhagya on 14/06/2016.
 */
public class BaseDataModel implements Serializable {

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
