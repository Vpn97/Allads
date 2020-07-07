package com.meghalayaads.allads.common.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.meghalayaads.allads.R;
import com.meghalayaads.allads.common.model.AdContent;
import com.meghalayaads.allads.common.model.AdMst;

/**
 * Allads
 * Created by Vishal Nagvadiya on 06-07-2020.
 */
public class AdShareUtil {

    public static final int WHATSAPP = 0;
    public static final int TWITTER = 1;
    public static final int FACEBOOK = 3;
    public static final int INSTAGRAM = 4;



    public static   void shareAdPost(Context context, AdContent adContent, AdMst mst, Integer SHARE_OPTION){



        switch (SHARE_OPTION){
            case WHATSAPP:
                    shareToWhatsApp(context,adContent,mst);
                break;
            case FACEBOOK:

                break;
            case INSTAGRAM:

                break;
            case TWITTER:

                break;

            default:
                break;
        }





    }




    public static void shareToWhatsApp(Context context, AdContent adContent, AdMst mst) {
        String finalAdText = getAdText(mst, context);
        if (adContent.isImage()) {
            //share only Image


            Glide.with(context)
                    .asBitmap()
                    .load(adContent.getImageDtl().getImgURL())
                    .into(new SimpleTarget<Bitmap>() {

                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            try {
                                String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), resource, "", null);
                                Uri imgUri = Uri.parse(path);
                                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                                shareIntent.setType("*/*");
                                shareIntent.setPackage("com.whatsapp");
                                shareIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
                                shareIntent.putExtra(Intent.EXTRA_TEXT, finalAdText);

                                context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.share_using)));
                            } catch (Exception e) {
                                Log.d(AdsConstant.TAG, "onResourceReady: " + e.getMessage());
                            }
                        }
                    });
        }else{

            if(mst.isAdHaveImg()){
                String url =mst.getImages().get(0).getImgURL();
                if(null!=url && !TextUtils.isEmpty(url))
                Glide.with(context)
                        .asBitmap()
                        .load(url)
                        .into(new SimpleTarget<Bitmap>() {

                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                try {
                                    String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), resource, "", null);
                                    Uri imgUri = Uri.parse(path);
                                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                                    shareIntent.setType("*/*");
                                    shareIntent.setPackage("com.whatsapp");
                                    shareIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
                                    shareIntent.putExtra(Intent.EXTRA_TEXT, finalAdText);

                                    context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.share_using)));
                                } catch (Exception e) {
                                    Log.d(AdsConstant.TAG, "onResourceReady: " + e.getMessage());
                                }
                            }
                        });

            }else {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share ads from Allads");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, getAdText(mst, context));
                sharingIntent.setPackage("com.whatsapp");
                context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.share_using)));
            }

        }
    }


    public static String getAdText(AdMst mst,Context context){
        String adText = "";
        if (mst.getAdText() != null) {
            adText = mst.getAdText();
        }
        adText += "\n\n" + context.getString(R.string.download_now) + "https://play.google.com/store/apps/details?id=" + context.getPackageName();
        return adText;
    }




}
