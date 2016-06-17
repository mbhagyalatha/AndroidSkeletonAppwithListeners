package com.imaginnovatestructureapp.datamodels.ResponseModels;

import android.os.Bundle;

import com.google.gson.annotations.SerializedName;
import com.imaginnovatestructureapp.datamodels.data.BaseDataInterface;
import com.imaginnovatestructureapp.datamodels.data.ErrorMsg;
import com.imaginnovatestructureapp.datamodels.data.Message;
import com.imaginnovatestructureapp.network.webservices.ServiceConstants;


import java.util.ArrayList;

/**
 * Created by bhagya on 14/06/2016.
 */
public class AccountRegistrationReponse extends BaseResponse {


    @SerializedName("auth_token")
    private String auth_token;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("isregistered")
    private boolean isregistered;

    @SerializedName("email")
    private String email;

    @SerializedName("phonenumber")
    private String phonenumber;

    @SerializedName("street")
    private String street;

    @SerializedName("city")
    private String city;

    @SerializedName("state")
    private String state;

    @SerializedName("zipcode")
    private String zipcode;


    public AccountRegistrationReponse(Boolean success, ArrayList<ErrorMsg> errors, ArrayList<Message> messages, String auth_token, String firstname, String lastname, boolean isRegistered, String email, String phonenumber, String street, String city, String state, String zipcode) {
        super(success, errors, messages);
        this.auth_token = auth_token;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isregistered = isRegistered;
        this.email = email;
        this.phonenumber = phonenumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }


    public ArrayList<? extends BaseDataInterface> getData() {

        if (getSuccess()) {

            if (getMessages() != null && getMessages().size() > 0)
                return getMessages();
            else
                return null;

        } else {

            return getErrors();

        }


    }

    @Override
    public Bundle getOtherInfo() {
        Bundle b = new Bundle();
        b.putString(ServiceConstants.firstname, firstname);
        b.putString(ServiceConstants.lastname, lastname);
        b.putString(ServiceConstants.authtoken, auth_token);
        b.putBoolean(ServiceConstants.isRegistered, true);
        b.putString(ServiceConstants.email, email);
        b.putString(ServiceConstants.phonenumber, phonenumber);
        b.putString(ServiceConstants.street, street);
        b.putString(ServiceConstants.city, city);
        b.putString(ServiceConstants.state, state);
        b.putString(ServiceConstants.zipcode, zipcode);
        return b;
    }


}
