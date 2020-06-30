package com.meghalayaads.allads.user.viewmodel.registation;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.meghalayaads.allads.user.events.OnVerifyOTPEvent;
import com.meghalayaads.allads.user.response.registration.StatusResponse;
import com.meghalayaads.allads.user.service.registration.RegistrationService;
import com.meghalayaads.allads.user.service.registration.RegistrationServiceImpl;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Allads
 * Created by Vishal Nagvadiya on 30-06-2020.
 */

public class ActivityVerifyOTPViewModel  extends AndroidViewModel {

    private Application application;
    private MutableLiveData<String> otp = new MutableLiveData<>();
    private OnVerifyOTPEvent otpEvent;

    public ActivityVerifyOTPViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }


    public Application getApplication() {
        return application;
    }


    public void setIsMobileVerify(String mobNo){

        RegistrationService service= RegistrationServiceImpl.getService();
        HashMap<String, String> map = new HashMap<>();
        map.put("mob_no", mobNo);
        service.setIsMobileVerify(map).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                Toast.makeText(application, mobNo+" verified", Toast.LENGTH_SHORT).show();
                otpEvent.onMobileVerifyFailDB();
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                otpEvent.onMobileVerifyFailDB();
            }
        });

    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public MutableLiveData<String> getOtp() {
        return otp;
    }

    public void setOtp(MutableLiveData<String> otp) {
        this.otp = otp;
    }

    public OnVerifyOTPEvent getOtpEvent() {
        return otpEvent;
    }

    public void setOtpEvent(OnVerifyOTPEvent otpEvent) {
        this.otpEvent = otpEvent;
    }
}
