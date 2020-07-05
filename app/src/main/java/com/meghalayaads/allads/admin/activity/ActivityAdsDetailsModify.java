package com.meghalayaads.allads.admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.meghalayaads.allads.R;
import com.meghalayaads.allads.admin.event.OnModifyAdsPriceEvent;
import com.meghalayaads.allads.admin.model.AdminMst;
import com.meghalayaads.allads.admin.model.AdsPriceMst;
import com.meghalayaads.allads.admin.response.AdsPriceUpdateResponse;
import com.meghalayaads.allads.admin.viewmodel.AdsDetailsModifyViewModel;
import com.meghalayaads.allads.common.util.DataStorage;
import com.meghalayaads.allads.common.util.Error;
import com.meghalayaads.allads.databinding.ActivityAdsDetailsModifyBinding;
import com.meghalayaads.allads.databinding.BottomsheetAdsDetailsModifyBinding;

import java.util.ArrayList;
import java.util.Date;

public class ActivityAdsDetailsModify extends AppCompatActivity implements OnModifyAdsPriceEvent {


    private AdsPriceMst adsPriceMst;
    private AdminMst adminMst;
    private DataStorage storage;
    private ActivityAdsDetailsModifyBinding binding;
    private AdsDetailsModifyViewModel model;
    private BottomSheetDialog dialog;
    private BottomsheetAdsDetailsModifyBinding bottomBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_details_modify);

        Intent intent=getIntent();
        if(null!=intent && null!=intent.getParcelableExtra(getString(R.string.key_ads_price_mst))){
            adsPriceMst=intent.getParcelableExtra(getString(R.string.key_ads_price_mst));
        }else{
            Toast.makeText(this, "unable to process", Toast.LENGTH_SHORT).show();
            finish();
        }

        storage=new DataStorage(this,getString(R.string.key_user_data));
        adminMst =new Gson().fromJson(String.valueOf(storage.read(getString(R.string.key_admin_mst), DataStorage.STRING)),AdminMst.class);


        allocation();
        setEvent();

    }

    private void allocation() {
        binding= DataBindingUtil.setContentView(this,R.layout.activity_ads_details_modify);
        model= ViewModelProviders.of(this).get(AdsDetailsModifyViewModel.class);
        binding.setModel(model);
        model.setEvent(this);

        binding.txtAdsTypeTitile.setText(String.format("%s ads price & limit", adsPriceMst.getUserType().getTypeName()));
    }

    private void setEvent() {


        model.getPricePerWord().observe(this, s -> {

                try {
                    double aDouble=Double.parseDouble(s);
                    binding.txtPricePerWord.setError(null);
                    model.getAdsPriceMstLiveData().getValue().setAmountPerWord(s);
                }catch (Exception e){
                 binding.txtPricePerWord.setError(getString(R.string.plase_enter_valid_value));
                }
            validateField();
        });


        model.getLumpSumAmount().observe(this, s -> {
                try {
                    double aDouble=Double.parseDouble(s);
                    binding.txtLumpSumAmount.setError(null);
                    model.getAdsPriceMstLiveData().getValue().setLumpSumAmount(s);
                }catch (Exception e){
                    binding.txtLumpSumAmount.setError(getString(R.string.plase_enter_valid_value));
                }
            validateField();
        });


        model.getPricePerImg() .observe(this, s -> {
                try {
                    double aDouble=Double.parseDouble(s);
                    binding.txtPricePerImg.setError(null);
                    model.getAdsPriceMstLiveData().getValue().setAmountPerImg(s);
                }catch (Exception e){
                    binding.txtPricePerImg.setError(getString(R.string.plase_enter_valid_value));
                }
            validateField();
        });


        model.getWordLimit() .observe(this, s -> {

                try {
                    int anInt=Integer.parseInt(s);
                    binding.txtWordLimit.setError(null);
                     model.getAdsPriceMstLiveData().getValue().setWordLimit(s);
                }catch (Exception e){
                    binding.txtWordLimit.setError(getString(R.string.plase_enter_valid_value));
                }
            validateField();

        });


        model.getAdsTimeLimitDays() .observe(this, s -> {

                try {
                    int anInt=Integer.parseInt(s);
                    binding.txtAdsDayLimit.setError(null);
                    model.getAdsPriceMstLiveData().getValue().setAdsTimeLimitDays(s);
                }catch (Exception e){
                    binding.txtAdsDayLimit.setError(getString(R.string.plase_enter_valid_value));
                }
            validateField();
        });

        model.getLumpSumWordLimit() .observe(this, s -> {
                try {
                    int anInt=Integer.parseInt(s);
                    binding.txtLumpSumWordLimit.setError(null);
                    model.getAdsPriceMstLiveData().getValue().setLumpSumWordLimit(s);
                }catch (Exception e){
                    binding.txtLumpSumWordLimit.setError(getString(R.string.plase_enter_valid_value));
                }
            validateField();
        });




        model.getPricePerWord().setValue(adsPriceMst.getAmountPerWord());
        model.getWordLimit().setValue(adsPriceMst.getWordLimit());
        model.getLumpSumAmount().setValue(adsPriceMst.getLumpSumAmount());
        model.getLumpSumWordLimit().setValue(adsPriceMst.getLumpSumWordLimit());
        model.getPricePerImg().setValue(adsPriceMst.getAmountPerImg());
        model.getAdsTimeLimitDays().setValue(adsPriceMst.getAdsTimeLimitDays());

        model.getAdsPriceMstLiveData().getValue().setAdminId(adminMst.getAdminId());
        model.getAdsPriceMstLiveData().getValue().setActive(true);
        model.getAdsPriceMstLiveData().getValue().setUserTypeId(adsPriceMst.getUserTypeId());
        model.getAdsPriceMstLiveData().getValue().setAdsPriceMstId(adsPriceMst.getAdsPriceMstId());
        model.getAdsPriceMstLiveData().getValue().setCreatedDate(new Date());

        validateField();

        binding.btnCancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
        binding.btnBack.setOnClickListener(v -> {
            binding.btnCancel.performClick();
        });

        binding.btnSave.setOnClickListener(v -> {

            confirmSubmitDialog();

        });
    }

    private void confirmSubmitDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View popupView = inflater.inflate(R.layout.bottomsheet_ads_details_modify, null);
        bottomBinding=BottomsheetAdsDetailsModifyBinding.inflate(inflater, (ViewGroup) popupView,false);
        dialog=new BottomSheetDialog(this,R.style.CustomBottomSheet);
        dialog.setContentView(bottomBinding.getRoot());
        dialog.show();

        bottomBinding.setMst(model.getAdsPriceMstLiveData().getValue());

        bottomBinding.btnCancel2.setOnClickListener(v -> dialog.dismiss());

        bottomBinding.btnConfirmSave.setOnClickListener(v -> {

            model.updateAdsPrice(adminMst.getMobNo());

        });
    }



    public void startProgressbar(){
        binding.consRoot.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);


    }

    public void stopProgressbar(){
        binding.consRoot.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onStartUpdate() {
        startProgressbar();
        if(dialog!=null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    @Override
    public void onSuccess(AdsPriceUpdateResponse response) {
        stopProgressbar();
        if(response!=null && response.isStatus()){
            Intent intent=new Intent();
            intent.putExtra(getString(R.string.key_updated_ad_price_mst),response.getAdsPriceMst());
            setResult(RESULT_OK,intent);
            finish();
        }

    }

    @Override
    public void onFail(ArrayList<Error> errors) {
        stopProgressbar();
        if(errors!=null && !errors.isEmpty()){
            final AlertDialog.Builder builder = new AlertDialog.Builder(ActivityAdsDetailsModify.this);
            LayoutInflater inflater = LayoutInflater.from(ActivityAdsDetailsModify.this);
            final View errorView = inflater.inflate(R.layout.dialog_price_update_error, null);
            builder.setView(errorView);

            AlertDialog alertDialog=builder.create();

            Error error=errors.get(0);
            TextView view=errorView.findViewById(R.id.txtError);
            view.setText(error.getMessage());
            Button btnOk = errorView.findViewById(R.id.btnOk);
            btnOk.setOnClickListener(v -> {
                alertDialog.dismiss();
            });
            alertDialog.show();
        }
    }



    public void validateField(){

        try {
            double d1= Double.parseDouble(binding.txtPricePerWord.getEditText().getText().toString());
            double d2= Double.parseDouble(binding.txtPricePerImg.getEditText().getText().toString());
            double d3= Double.parseDouble(binding.txtLumpSumAmount.getEditText().getText().toString());
            int v1= Integer.parseInt(binding.txtWordLimit.getEditText().getText().toString());
            int v2= Integer.parseInt(binding.txtLumpSumWordLimit.getEditText().getText().toString());
            int v3= Integer.parseInt(binding.txtAdsDayLimit.getEditText().getText().toString());

            binding.btnSave.setEnabled(true);
        }catch (Exception e){
            binding.btnSave.setEnabled(false);
        }
    }

}