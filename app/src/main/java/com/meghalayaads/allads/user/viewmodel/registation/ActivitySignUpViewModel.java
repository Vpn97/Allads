package com.meghalayaads.allads.user.viewmodel.registation;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.meghalayaads.allads.R;
import com.meghalayaads.allads.common.model.UserType;
import com.meghalayaads.allads.common.response.UserTypeResponse;
import com.meghalayaads.allads.common.services.CommonServices;
import com.meghalayaads.allads.common.services.CommonServicesImpl;
import com.meghalayaads.allads.common.util.AdsConstant;
import com.meghalayaads.allads.common.util.Error;
import com.meghalayaads.allads.user.activity.registration.ActivitySignUp;
import com.meghalayaads.allads.user.events.OnSignUpEvent;
import com.meghalayaads.allads.user.model.SignUpModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Allads
 * Created by Vishal Nagvadiya on 27-06-2020.
 */
public class ActivitySignUpViewModel extends AndroidViewModel {
    private Application application;
    private MutableLiveData<ArrayList<UserType>> userTypesLiveData = new MutableLiveData<>();
    private MutableLiveData<SignUpModel> signUpLiveData = new MutableLiveData<>();
    private OnSignUpEvent event;

    public ActivitySignUpViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        signUpLiveData.setValue(new SignUpModel());
    }


    public void getUserTypes() {
        CommonServices commonServices = CommonServicesImpl.getService();
        commonServices.getUserTypes().enqueue(new Callback<UserTypeResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserTypeResponse> call, @NonNull Response<UserTypeResponse> response) {
                if (null != response.body() && response.body().isStatus()) {
                    userTypesLiveData.setValue(response.body().getUserTypes());
                }
            }

            @Override
            public void onFailure(Call<UserTypeResponse> call, Throwable t) {
                Log.d(AdsConstant.TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    public boolean validateUserData() {
        SignUpModel data = signUpLiveData.getValue();
        ArrayList<Error> errors = new ArrayList<>();

        //validate mobile number
        if (null == data.getMobileNumber() ||
                TextUtils.isEmpty(data.getMobileNumber().trim()) ||
                !TextUtils.isDigitsOnly(data.getMobileNumber().trim()) ||
                data.getMobileNumber().trim().length() != 10) {
            errors.add(new Error(ActivitySignUp.SING_UP_ERROR_CODE.ERR001.toString(),
                    application.getString(R.string.validate_mobile_number), "REG"));
        }

        //validate user name
        if (null == data.getUserName() || TextUtils.isEmpty(data.getUserName().trim())) {
            errors.add(new Error(ActivitySignUp.SING_UP_ERROR_CODE.ERR002.toString(),
                    application.getString(R.string.enter_user_name), "REG"));
        }


        //password
        //password validation
        if (null == data.getPassword() || TextUtils.isEmpty(data.getPassword().trim()) ||
                data.getPassword().trim().length() < 6 || data.getPassword().trim().length() > 20) {

            errors.add(new Error(ActivitySignUp.SING_UP_ERROR_CODE.ERR003.toString(),
                    application.getString(R.string.password_can_be), "REG"));
        } else {
            if (null == data.getConfirmPassword().trim() || !data.getConfirmPassword().trim().equals(data.getPassword().trim())) {
                errors.add(new Error(ActivitySignUp.SING_UP_ERROR_CODE.ERR004.toString(),
                        application.getString(R.string.password_not_match), "REG"));
            }
        }


        event.setErrorUI(errors);
        return errors.size() == 0;
    }


    public MutableLiveData<SignUpModel> getSignUpLiveData() {
        return signUpLiveData;
    }

    public void setSignUpLiveData(MutableLiveData<SignUpModel> signUpLiveData) {
        this.signUpLiveData = signUpLiveData;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public MutableLiveData<ArrayList<UserType>> getUserTypesLiveData() {
        return userTypesLiveData;
    }

    public void setUserTypesLiveData(MutableLiveData<ArrayList<UserType>> userTypesLiveData) {
        this.userTypesLiveData = userTypesLiveData;
    }

    public OnSignUpEvent getEvent() {
        return event;
    }

    public void setEvent(OnSignUpEvent event) {
        this.event = event;
    }
}
