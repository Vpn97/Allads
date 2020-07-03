package com.meghalayaads.allads.admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.meghalayaads.allads.R;
import com.meghalayaads.allads.admin.model.AdsPriceMst;
import com.meghalayaads.allads.admin.viewmodel.AdsDetailsModifyViewModel;
import com.meghalayaads.allads.databinding.ActivityAdsDetailsModifyBinding;

public class ActivityAdsDetailsModify extends AppCompatActivity {


    private AdsPriceMst adsPriceMst;
    private ActivityAdsDetailsModifyBinding binding;
    private AdsDetailsModifyViewModel model;

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


        allocation();
        setEvent();

    }

    private void allocation() {
        binding= DataBindingUtil.setContentView(this,R.layout.activity_ads_details_modify);
        model= ViewModelProviders.of(this).get(AdsDetailsModifyViewModel.class);
        binding.setModel(model);

        binding.txtAdsTypeTitile.setText(String.format("%s ads price & limit", adsPriceMst.getUserType().getTypeName()));
    }

    private void setEvent() {


        model.getPricePerWord().observe(this, s -> {

                try {
                    double aDouble=Double.parseDouble(s);
                    binding.txtPricePerWord.setError(null);
                }catch (Exception e){
                 binding.txtPricePerWord.setError(getString(R.string.plase_enter_valid_value));
                }
        });


        model.getLumpSumAmount().observe(this, s -> {
                try {
                    double aDouble=Double.parseDouble(s);
                    binding.txtLumpSumAmount.setError(null);
                }catch (Exception e){
                    binding.txtLumpSumAmount.setError(getString(R.string.plase_enter_valid_value));
                }
        });


        model.getPricePerImg() .observe(this, s -> {
                try {
                    double aDouble=Double.parseDouble(s);
                    binding.txtPricePerImg.setError(null);
                }catch (Exception e){
                    binding.txtPricePerImg.setError(getString(R.string.plase_enter_valid_value));
                }
        });


        model.getWordLimit() .observe(this, s -> {

                try {
                    int anInt=Integer.parseInt(s);
                    binding.txtWordLimit.setError(null);
                }catch (Exception e){
                    binding.txtWordLimit.setError(getString(R.string.plase_enter_valid_value));
                }

        });


        model.getAdsTimeLimitDays() .observe(this, s -> {

                try {
                    int anInt=Integer.parseInt(s);
                    binding.txtAdsDayLimit.setError(null);

                }catch (Exception e){
                    binding.txtAdsDayLimit.setError(getString(R.string.plase_enter_valid_value));
                }
        });

        model.getLumpSumWordLimit() .observe(this, s -> {
                try {
                    int anInt=Integer.parseInt(s);
                    binding.txtLumpSumWordLimit.setError(null);
                }catch (Exception e){
                    binding.txtLumpSumWordLimit.setError(getString(R.string.plase_enter_valid_value));
                }
        });




        model.getPricePerWord().setValue(adsPriceMst.getAmountPerWord());
        model.getWordLimit().setValue(adsPriceMst.getWordLimit());
        model.getLumpSumAmount().setValue(adsPriceMst.getLumpSumAmount());
        model.getLumpSumWordLimit().setValue(adsPriceMst.getLumpSumWordLimit());
        model.getPricePerImg().setValue(adsPriceMst.getAmountPerImg());
        model.getAdsTimeLimitDays().setValue(adsPriceMst.getAdsTimeLimitDays());


        binding.btnCancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
        binding.btnBack.setOnClickListener(v -> {
            binding.btnCancel.performClick();
        });

        binding.btnSave.setOnClickListener(v -> {


        });
    }




}