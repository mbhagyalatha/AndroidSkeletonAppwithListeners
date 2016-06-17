package com.imaginnovatestructureapp.network.webservices;

import android.os.Bundle;

/**
 * Created by bhagya on 14/06/2016.
 */
public class AccountRegistrationWS extends BaseMsmWsImpl implements NetworkCallInterface {

    public AccountRegistrationWS(int reqId, ResultListener listener) {
        super(reqId, listener);
    }

    @Override
    public void sendRequestToServer(Bundle data) {

        sendRequest(request_type.POST, data);
    }

}
