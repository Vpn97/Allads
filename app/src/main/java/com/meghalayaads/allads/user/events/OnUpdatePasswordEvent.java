package com.meghalayaads.allads.user.events;

import com.meghalayaads.allads.common.util.Error;
import com.meghalayaads.allads.user.response.registration.StatusResponse;

import java.util.ArrayList;

/**
 * Allads
 * Created by Vishal Nagvadiya on 01-07-2020.
 */
public interface OnUpdatePasswordEvent {

    public void onStartUpdatePassword();
    public void onPasswordUpdateSuccessful(StatusResponse response);
    public void onPasswordUpdateFail(StatusResponse response);

    void setErrorUI(ArrayList<Error> errors);
}
