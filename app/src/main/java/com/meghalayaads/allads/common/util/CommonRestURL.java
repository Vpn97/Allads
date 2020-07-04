package com.meghalayaads.allads.common.util;

/**
 * Allads
 * Created by Vishal Nagvadiya on 27-06-2020.
 */
public class CommonRestURL {
    public static final String BASE_DOMAIN="http://192.168.43.81/";
    public static final String BASE_SERVICE_URL="allads/service/";

    public static final String BASE_URL=BASE_DOMAIN+BASE_SERVICE_URL;


    //common
    public static final String GET_USER_TYPES="common/getUserTypes.php";


    //registration
    public static final String REG_USER_MST="registration/regUserMst.php";
    public static final String IS_MOB_NO_EXIST="registration/isMobileNumExist.php";
    public static final String SET_MOB_NO_VERIFY="registration/setIsMobileVerify.php";
    public static final String LOGIN_REQ="registration/loginreq.php";
    public static final String UPDATE_PASSWORD="registration/updateUserPassword.php";
    public static final String ADMIN_LOGIN_REQ="registration/adminLoginReq.php";


    //admin
    public static final String GET_ADMIN_REPORT="admin/getAdsReport.php";
    public static final String GET_ADS_PRICE_MST = "admin/getAdsPriceMst.php";
    public static final String UPDATE_ADS_PRICE = "admin/updateAdsPrices.php";
}
