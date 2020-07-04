package com.meghalayaads.allads.admin.activity.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.meghalayaads.allads.R;
import com.meghalayaads.allads.admin.event.SettingAdapterEvent;
import com.meghalayaads.allads.admin.model.AdsPriceMst;
import com.meghalayaads.allads.common.util.AdsConstant;
import com.meghalayaads.allads.databinding.ItemAdsSettingsBinding;

import java.util.ArrayList;

/**
 * Allads
 * Created by Vishal Nagvadiya on 03-07-2020.
 */
public class AdsTypesListAdapter extends RecyclerView.Adapter<AdsTypesListAdapter.AdTypeViewHolder>{

    private ArrayList<AdsPriceMst> adsPriceMsts;
    private Context context;
    private SettingAdapterEvent event;

    public AdsTypesListAdapter(ArrayList<AdsPriceMst> adsPriceMsts, Context context, SettingAdapterEvent event) {
        this.adsPriceMsts = adsPriceMsts;
        this.context = context;
        this.event =event;
    }

    @NonNull
    @Override
    public AdTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAdsSettingsBinding binding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_ads_settings,parent,false);
        return new AdTypeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdTypeViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_recycler_item_show);
        holder.itemView.startAnimation(animation);
        AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
        aa.setDuration(500);
        holder.setData(adsPriceMsts.get(position),position);
    }

    @Override
    public int getItemCount() {
        if(null!=adsPriceMsts) {
            return adsPriceMsts.size();
        }
        return  0;
    }

    public class AdTypeViewHolder extends RecyclerView.ViewHolder{

        ItemAdsSettingsBinding binding;
        public AdTypeViewHolder(@NonNull ItemAdsSettingsBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void setData(AdsPriceMst adsPriceMst,int index) {
            binding.setMst(adsPriceMst);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.crdTypeback.setBackgroundColor(context.getColor(AdsConstant.getColorList(index)[0]));
                binding.txtTypeCode.setTextColor(context.getColor(AdsConstant.getColorList(index)[1]));
                binding.txtAdTypeTitle.setTextColor(context.getColor(AdsConstant.getColorList(index)[1]));
            }

            binding.getRoot().setOnClickListener(v -> {
                event.setActivityForResult(adsPriceMst);
            });

        }
    }

}
