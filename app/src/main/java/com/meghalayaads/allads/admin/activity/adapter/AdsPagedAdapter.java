package com.meghalayaads.allads.admin.activity.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.meghalayaads.allads.R;
import com.meghalayaads.allads.common.model.AdMst;
import com.meghalayaads.allads.databinding.ItemAdLayoutBinding;

/**
 * Allads
 * Created by Vishal Nagvadiya on 05-07-2020.
 */
public class AdsPagedAdapter extends PagedListAdapter<AdMst,AdsPagedAdapter.AdMstViewHolder>  {

    private Context context;

    public AdsPagedAdapter(Context context) {
        super(AdMst.CALLBACK);
        this.context=context;
    }


    @NonNull
    @Override
    public AdMstViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAdLayoutBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_ad_layout,parent,false);

        return new AdMstViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdMstViewHolder holder, int position) {
        AdMst mst=getItem(position);
        if(mst!=null)
        holder.SetData(mst);
    }

    public class AdMstViewHolder extends RecyclerView.ViewHolder{

        private ItemAdLayoutBinding mBinding;

        public AdMstViewHolder(@NonNull ItemAdLayoutBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding=mBinding;
        }

        public void SetData(AdMst mst){
            mBinding.txtUserName.setText(mst.getUserDtl().getUserName());
            mBinding.txtCat.setText(mst.getCategoryDtl().getCategoryName());
            mBinding.txtSubCat.setText(mst.getSubCategoryDtl().getSubCategoryName());
            mBinding.txtTimeAgo.setText(mst.getCreatedDate().toLocaleString());


            if(null!=mst.getImages() && !mst.getImages().isEmpty()) {
                Glide.with(context)
                        .load(Uri.parse(mst.getImages() .get(0).getImgURL()))
                        .thumbnail(Glide // this thumbnail request has to have the same RESULT cache key
                                .with(context) // as the outer request, which usually simply means
                                .load(Uri.parse(mst.getImages() .get(0).getImgURL())) // same size/transformation(e.g. centerCrop)/format(e.g. asBitmap)
                                .fitCenter())
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                //mBinding.wallpaperLoading.setVisibility(View.INVISIBLE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        })
                        .into(mBinding.imgAd);
            }else{

            }
        }

    }
}
