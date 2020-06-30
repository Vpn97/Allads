package com.meghalayaads.allads.common.util;

import android.text.TextUtils;

/**
 * Allads
 * Created by Vishal Nagvadiya on 30-06-2020.
 */
public class CommonUtil {

    public static boolean isValidMobNo(String num){

        if(null==num){
            return false;
        }

        num=num.trim();
        if(num!=null && !TextUtils.isEmpty(num) && num.length()==10){

            for (int i = 0; i < num.length(); i++) {
                if(!TextUtils.isDigitsOnly(String.valueOf(num.charAt(i)))){
                    return false;
                }
            }

            return true;
        }

        return false;
    }

}
