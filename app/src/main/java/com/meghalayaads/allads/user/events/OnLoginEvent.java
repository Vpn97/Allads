package com.meghalayaads.allads.user.events;

import com.meghalayaads.allads.common.util.Error;
import com.meghalayaads.allads.user.response.registration.UserRegResponse;

import java.util.ArrayList;

/**
 * Allads
 * Created by Vishal Nagvadiya on 24-06-2020.
 */
public interface OnLoginEvent {

    public void onLoginStart();
    public void onLoginSuccess(UserRegResponse response);
    public void onLoginFail(UserRegResponse response);

    public void setErrorUI(ArrayList<Error> errors);
    public void setForgetPasswordUIError(ArrayList<Error> errors,String mobNo);
}
