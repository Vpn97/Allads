package com.meghalayaads.allads.admin.event;

import com.meghalayaads.allads.admin.response.AdsPriceUpdateResponse;
import com.meghalayaads.allads.common.util.Error;

import java.util.ArrayList;

/**
 * Allads
 * Created by Vishal Nagvadiya on 04-07-2020.
 */
public interface OnModifyAdsPriceEvent {

    public void onStartUpdate();
    public void onSuccess(AdsPriceUpdateResponse response);
    public void onFail(ArrayList<Error> errors);

}
