package com.meghalayaads.allads.admin.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.meghalayaads.allads.R;
import com.meghalayaads.allads.common.model.AdContent;
import com.meghalayaads.allads.common.model.AdMst;
import com.meghalayaads.allads.common.util.AdShareUtil;
import com.meghalayaads.allads.common.util.AdUtil;
import com.meghalayaads.allads.common.util.AdsConstant;
import com.meghalayaads.allads.databinding.ItemAdLayoutBinding;

import java.util.ArrayList;

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
        if(mst!=null) {
            holder.SetData(mst);
            //Toast.makeText(context, "size : "+AdUtil.getAdContentFromAdMst(mst).size(), Toast.LENGTH_SHORT).show();

            ArrayList<AdContent> adContents=AdUtil.getAdContentFromAdMst(mst);
            AdViewPagerAdapter pagerAdapter=new AdViewPagerAdapter(adContents,context);
            holder.mBinding.viewPagerAdContent.setAdapter(pagerAdapter);

            setShareBtnClicks(holder.mBinding,mst,adContents);


            holder.mBinding.viewPagerAdContent.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                    pagerAdapter.notifyDataSetChanged();

                }

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    /*View view=  holder.mBinding.viewPagerAdContent.getChildAt(position);
                    if(view!=null)
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            int wMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY);
                            int hMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                            view.measure(wMeasureSpec, hMeasureSpec);
                            if (holder.mBinding.viewPagerAdContent.getLayoutParams().height != view.getMeasuredHeight()) {
                                // ParentViewGroup is, for example, LinearLayout
                                // ... or whatever the parent of the ViewPager2 is
                                ConstraintLayout.LayoutParams params= new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,view.getMeasuredHeight());
                                holder.mBinding.viewPagerAdContent.setLayoutParams(params);
                            }
                        }
                    });*/
                    pagerAdapter.notifyDataSetChanged();

                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    super.onPageScrollStateChanged(state);
                }
            });
        }
    }



    public class AdMstViewHolder extends RecyclerView.ViewHolder{

        private ItemAdLayoutBinding mBinding;

        public AdMstViewHolder(@NonNull ItemAdLayoutBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding=mBinding;
        }

        public void SetData(AdMst mst){
            /*
            mBinding.txtUserName.setText(mst.getUserDtl().getUserName());
            mBinding.txtCat.setText(mst.getCategoryDtl().getCategoryName());
            mBinding.txtSubCat.setText(mst.getSubCategoryDtl().getSubCategoryName());
            mBinding.txtTimeAgo.setText(mst.getCreatedDate().toLocaleString());*/
            mBinding.imgUser.setImageResource(AdsConstant.getRandomAvatar());
            mBinding.setMst(mst);


            }

    }


    private void setShareBtnClicks(ItemAdLayoutBinding mBinding, AdMst mst,ArrayList<AdContent> adContents) {



        mBinding.btnFacebook.setOnClickListener(v->{
            int index =mBinding.viewPagerAdContent.getCurrentItem();
            AdContent currentContent=adContents.get(index);
            AdShareUtil.shareAdPost(context,currentContent,mst,AdShareUtil.FACEBOOK);        });


        mBinding.btnWhatsapp.setOnClickListener(v -> {
            int index =mBinding.viewPagerAdContent.getCurrentItem();
            AdContent currentContent=adContents.get(index);
            AdShareUtil.shareAdPost(context,currentContent,mst,AdShareUtil.WHATSAPP);
        });

        mBinding.btnInsta.setOnClickListener(v -> {
            int index =mBinding.viewPagerAdContent.getCurrentItem();
            AdContent currentContent=adContents.get(index);
            AdShareUtil.shareAdPost(context,currentContent,mst,AdShareUtil.INSTAGRAM);
        });

        mBinding.btnTwiter.setOnClickListener(v -> {
            int index =mBinding.viewPagerAdContent.getCurrentItem();
            AdContent currentContent=adContents.get(index);
            AdShareUtil.shareAdPost(context,currentContent,mst,AdShareUtil.TWITTER);
        });

    }



}
