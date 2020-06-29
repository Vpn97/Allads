package com.meghalayaads.allads.user.events;

/**
 * Allads
 * Created by Vishal Nagvadiya on 24-06-2020.
 */
public interface LoginEvent {

    public void onLoginSuccess();
    public void onLoginFail();
}
