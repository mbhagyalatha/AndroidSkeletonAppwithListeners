package com.imaginnovatestructureapp.network.webservices;

/**
 * Created by Abhishek on 1/27/2015.
 */
public class ServiceConstants {
    //constant values
    public static final int SUCCESS = 1000;
    public static final int FAILURE = 1001;
    public static final int ERROR = 1002;


    //REQ TYPE
    public static final int LOGIN = 1;
    public static final int ACC_REGISTRATION = 2;

    //URL PARAMETER NAMES.
    public static final String firstname = "firstname";
    public static final String lastname = "lastname";
    public static final String email = "email";
    public static final String authtoken = "authtoken";
    public static final String phonenumber = "phonenumber";
    public static final String isRegistered = "isRegistered";
    public static final String street = "street";
    public static final String state = "state";
    public static final String city = "city";
    public static final String zipcode = "zipcode";
}
