package com.meghalayaads.allads.user.viewmodel.registation;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.meghalayaads.allads.R;
import com.meghalayaads.allads.common.util.Error;
import com.meghalayaads.allads.user.activity.registration.ActivityUpdatePassword;
import com.meghalayaads.allads.user.events.OnUpdatePasswordEvent;
import com.meghalayaads.allads.user.model.SignUpModel;
import com.meghalayaads.allads.user.response.registration.StatusResponse;
import com.meghalayaads.allads.user.service.registration.RegistrationService;
import com.meghalayaads.allads.user.service.registration.RegistrationServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Allads
 * Created by Vishal Nagvadiya on 01-07-2020.
 */
public class ActivityUpdatePasswordViewModel extends AndroidViewModel {

    private Application application;
    private MutableLiveData<SignUpModel> liveData=new MutableLiveData<>();
    private OnUpdatePasswordEvent event;


    public ActivityUpdatePasswordViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
        this.liveData.setValue(new SignUpModel());
    }



    public void updatePassword(){


        if(validateUserData()) {
            RegistrationService service = RegistrationServiceImpl.getService();
            HashMap<String, String> map = new HashMap<>();
            map.put("mob_no", liveData.getValue().getMobileNumber());
            map.put("password", liveData.getValue().getPassword());
            event.onStartUpdatePassword();
            service.updatePassword(map).enqueue(new Callback<StatusResponse>() {
                @Override
                public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                    if(response.body()!=null && response.body().isStatus()){
                        event.onPasswordUpdateSuccessful(response.body());
                    }else{
                        event.onPasswordUpdateFail(response.body());
                    }
                }

                @Override
                public void onFailure(Call<StatusResponse> call, Throwable t) {
                    StatusResponse response=new StatusResponse();
                    response.setStatus(false);
                    ArrayList<Error> errors=new ArrayList<>();
                    errors.add(new Error(ActivityUpdatePassword.UPDATE_PASS_ERROR_CODE.PASS006.toString(),t.getMessage(),"REG"));
                    response.setErrors(errors);
                    event.onPasswordUpdateFail(response);
                }
            });

        }

    }


    public boolean validateUserData() {
        SignUpModel data = liveData.getValue();
        ArrayList<Error> errors = new ArrayList<>();
        //password
        //password validation
        if (null == data.getPassword() || TextUtils.isEmpty(data.getPassword().trim()) ||
                data.getPassword().trim().length() < 6 || data.getPassword().trim().length() > 20) {

            errors.add(new Error(ActivityUpdatePassword.UPDATE_PASS_ERROR_CODE.PASS001.toString(),
                    application.getString(R.string.password_can_be), "REG"));
        } else {
            if (null == data.getConfirmPassword().trim() || !data.getConfirmPassword().trim().equals(data.getPassword().trim())) {
                errors.add(new Error(ActivityUpdatePassword.UPDATE_PASS_ERROR_CODE.PASS002.toString(),
                        application.getString(R.string.password_not_match), "REG"));
            }
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

    public MutableLiveData<SignUpModel> getLiveData() {
        return liveData;
    }

    public void setLiveData(MutableLiveData<SignUpModel> liveData) {
        this.liveData = liveData;
    }

    public OnUpdatePasswordEvent getEvent() {
        return event;
    }

    public void setEvent(OnUpdatePasswordEvent event) {
        this.event = event;
    }
}
