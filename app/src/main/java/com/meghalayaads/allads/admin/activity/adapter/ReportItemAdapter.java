package com.meghalayaads.allads.admin.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.meghalayaads.allads.R;
import com.meghalayaads.allads.databinding.ItemReportViewBinding;

import java.util.ArrayList;

/**
 * Allads
 * Created by Vishal Nagvadiya on 02-07-2020.
 */
public class ReportItemAdapter extends  RecyclerView.Adapter<ReportItemAdapter.ReportItemViewHolder>{

    private Context context;
    private ArrayList<String[]> arrayList;

    public ReportItemAdapter(Context context, ArrayList<String[]> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ReportItemAdapter.ReportItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemReportViewBinding binding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_report_view,parent,false);
        return new ReportItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportItemAdapter.ReportItemViewHolder holder, int position) {

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_recycler_item_show);
        holder.itemView.startAnimation(animation);
        AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
        aa.setDuration(400);

        final StaggeredGridLayoutManager.LayoutParams layoutParams =
                new StaggeredGridLayoutManager.LayoutParams(
                        holder.itemView.getLayoutParams());
        layoutParams.setFullSpan(false);

        if (position % 2 == 0) {
            //even
            layoutParams.setMargins(8, 16, 16, 0);
        } else {
            //odd
            layoutParams.setMargins(0, 16, 8, 0);
        }

        holder.itemView.setLayoutParams(layoutParams);

        holder.setData(arrayList.get(position),position);
    }

    @Override
    public int getItemCount() {
        if(arrayList !=null){
            return arrayList.size();
        }
        return 0;
    }

    public class ReportItemViewHolder extends RecyclerView.ViewHolder{
        private ItemReportViewBinding mBinding;
        public ReportItemViewHolder(@NonNull ItemReportViewBinding binding) {
            super(binding.getRoot());
            this.mBinding=binding;
        }

        public void setData(String[] data,int index){

            if(data!=null)
            mBinding.setTitle(data[0]);
            mBinding.setValue(data[1]);

           /* Integer[] colorList=AdsConstant.getColorList(index);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mBinding.crdRoot.setBackgroundColor(context.getColor(colorList[0]));
                mBinding.txtTitle.setTextColor(context.getColor(colorList[1]));
            }*/

        }

    }
}
