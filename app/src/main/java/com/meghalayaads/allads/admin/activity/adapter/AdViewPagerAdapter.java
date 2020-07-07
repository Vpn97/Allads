package com.meghalayaads.allads.admin.activity.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.meghalayaads.allads.R;
import com.meghalayaads.allads.common.model.AdContent;
import com.meghalayaads.allads.databinding.ItemAdContentViewPagerBinding;

import java.util.ArrayList;

/**
 * Allads
 * Created by Vishal Nagvadiya on 06-07-2020.
 */
public class AdViewPagerAdapter extends RecyclerView.Adapter<AdViewPagerAdapter.AdContentViewHolder>{


    private ArrayList<AdContent> adContents;
    private Context context;

    public AdViewPagerAdapter(ArrayList<AdContent> adContents, Context context) {
        this.adContents = adContents;
        this.context = context;
    }

    @NonNull
    @Override
    public AdContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAdContentViewPagerBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_ad_content_view_pager,parent,false);
        return new AdContentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdContentViewHolder holder, int position) {

        AdContent content=adContents.get(position);
        if(content!=null){
            holder.setData(content,position);
        }

    }

    @Override
    public int getItemCount() {
        if(adContents!=null && !adContents.isEmpty()) {
            return adContents.size();
        }else {
            return 0;
        }

    }

    public class AdContentViewHolder extends RecyclerView.ViewHolder{

       public ItemAdContentViewPagerBinding mBinding;

        public AdContentViewHolder(@NonNull ItemAdContentViewPagerBinding binding) {
            super(binding.getRoot());
            this.mBinding=binding;
        }

        public void setData(AdContent data,int position){

            if(data.isImage()){

                mBinding.adText.setVisibility(View.GONE);
                mBinding.adImage.setVisibility(View.VISIBLE);
                Uri uri= Uri.parse(data.getImageDtl().getImgURL());
                Glide.with(context)
                        .load(uri)
                        .into(mBinding.adImage);
            }else{
                mBinding.adText.setVisibility(View.VISIBLE);
                mBinding.adImage.setVisibility(View.GONE);

                if(data.getAdText()!=null){
                    mBinding.adText.setText(data.getAdText());
                }
            }

        }
    }
}
