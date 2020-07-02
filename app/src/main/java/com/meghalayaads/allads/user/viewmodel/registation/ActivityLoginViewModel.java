package com.meghalayaads.allads.user.viewmodel.registation;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.meghalayaads.allads.R;
import com.meghalayaads.allads.common.util.CommonUtil;
import com.meghalayaads.allads.common.util.Error;
import com.meghalayaads.allads.user.activity.registration.ActivityLogin;
import com.meghalayaads.allads.user.activity.registration.ActivitySignUp;
import com.meghalayaads.allads.user.events.OnLoginEvent;
import com.meghalayaads.allads.user.model.LoginModel;
import com.meghalayaads.allads.user.response.registration.StatusResponse;
import com.meghalayaads.allads.user.response.registration.UserRegResponse;
import com.meghalayaads.allads.user.service.registration.RegistrationService;
import com.meghalayaads.allads.user.service.registration.RegistrationServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Allads
 * Created by Vishal Nagvadiya on 24-06-2020.
 */
public class ActivityLoginViewModel extends AndroidViewModel {
    private Application application;
    private MutableLiveData<LoginModel> loginModelData=new MutableLiveData<>();
    private OnLoginEvent event;

    public ActivityLoginViewModel(@NonNull Application application) {
        super(application);
        loginModelData.setValue(new LoginModel());
        this.application=application;
    }



    public void loginRequest(){

        if(validateUserData()) {
            RegistrationService service = RegistrationServiceImpl.getService();
            HashMap<String, String> map = new HashMap<>();
            map.put("mob_no", loginModelData.getValue().getMobNo());
            map.put("password", loginModelData.getValue().getPassword());
            map.put("device_id", loginModelData.getValue().getDeviceId());


            service.loginRequest(map).enqueue(new Callback<UserRegResponse>() {
                @Override
                public void onResponse(Call<UserRegResponse> call, Response<UserRegResponse> response) {
                    if(null!=response.body() && response.body().isStatus()){
                        event.onLoginSuccess(response.body());
                    }else{
                        event.onLoginFail(response.body());
                    }
                }

                @Override
                public void onFailure(Call<UserRegResponse> call, Throwable t) {
                    ArrayList<Error> errors=new ArrayList<>();
                    errors.add(new Error(ActivityLogin.LOGIN_ERROR_CODE.LOGIN005.toString(),t.getMessage(),"REG"));
                    UserRegResponse regResponse=new UserRegResponse();
                    regResponse.setStatus(false);
                    regResponse.setErrors(errors);
                    event.onLoginFail(regResponse);
                }
            });
        }
    }




    public void isMobileNumberExist(String mobNo) {
        RegistrationService service=RegistrationServiceImpl.getService();
        HashMap<String, String> map = new HashMap<>();
        map.put("mob_no", mobNo);
        service.isMobileNumberExist(map).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if(response.body().isStatus()){
                    //exist
                    event.setForgetPasswordUIError(null,mobNo);
                }else{
                    //not exist
                    ArrayList<Error> errors=new ArrayList<>();
                    errors.add(new Error(ActivityLogin.LOGIN_ERROR_CODE.LOGIN003.toString(),application.getString(R.string.mobile_not_reg_sign_up_now),"REG"));
                    UserRegResponse regResponse=new UserRegResponse();
                    regResponse.setErrors(errors);
                    event.setForgetPasswordUIError(regResponse.getErrors(),mobNo);
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                ArrayList<Error> errors=new ArrayList<>();
                errors.add(new Error(ActivitySignUp.SING_UP_ERROR_CODE.REG005.toString(),t.getMessage(),"REG"));
                UserRegResponse regResponse=new UserRegResponse();
                regResponse.setStatus(false);
                regResponse.setErrors(errors);
                event.setForgetPasswordUIError(regResponse.getErrors(),mobNo);
            }
        });

    }



    public boolean validateUserData() {
        LoginModel data = loginModelData.getValue();
        ArrayList<Error> errors = new ArrayList<>();

        //validate mobile number
        if (!CommonUtil.isValidMobNo(data.getMobNo())) {
            errors.add(new Error(ActivityLogin.LOGIN_ERROR_CODE.LOGIN001.toString(),
                    application.getString(R.string.validate_mobile_number), "REG"));
        }
        //password
        //password validation
        if (null == data.getPassword() || TextUtils.isEmpty(data.getPassword().trim()) ||
                data.getPassword().trim().length() < 6 || data.getPassword().trim().length() > 20) {

            errors.add(new Error(ActivityLogin.LOGIN_ERROR_CODE.LOGIN002.toString(),
                    application.getString(R.string.password_can_be), "REG"));
        }


        event.setErrorUI(errors);
        return errors.size() == 0;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public MutableLiveData<LoginModel> getLoginModelData() {
        return loginModelData;
    }

    public void setLoginModelData(MutableLiveData<LoginModel> loginModelData) {
        this.loginModelData = loginModelData;
    }

    public OnLoginEvent getEvent() {
        return event;
    }

    public void setEvent(OnLoginEvent event) {
        this.event = event;
    }
}
