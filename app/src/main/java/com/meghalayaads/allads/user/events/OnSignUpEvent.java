package com.meghalayaads.allads.user.events;

import com.meghalayaads.allads.common.util.Error;

import java.util.ArrayList;

/**
 * Allads
 * Created by Vishal Nagvadiya on 27-06-2020.
 */
public interface OnSignUpEvent {

    public void setErrorUI(ArrayList<Error> errors);

}
