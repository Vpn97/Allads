package com.meghalayaads.allads.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import com.meghalayaads.allads.common.model.AdContent;
import com.meghalayaads.allads.common.model.AdImageDtl;
import com.meghalayaads.allads.common.model.AdMst;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Allads
 * Created by Vishal Nagvadiya on 06-07-2020.
 */
public class AdUtil {


    public static ArrayList<AdContent> getAdContentFromAdMst(AdMst mst) {

        ArrayList<AdContent> adContents = new ArrayList<>();

        if (mst.getAdText() != null && !TextUtils.isEmpty(mst.getAdText())) {
            AdContent content=new AdContent();
            content.setAdId(mst.getAdId());
            content.setUid(mst.getUid());
            content.setImage(false);
            content.setAdText(mst.getAdText());

            adContents.add(content);
        }

        if(mst.getImages()!=null && !mst.getImages().isEmpty()) {

            for (AdImageDtl imageDtl : mst.getImages()) {
                AdContent content=new AdContent();
                content.setAdId(mst.getAdId());
                content.setUid(mst.getUid());
                content.setImage(true);
                content.setImageDtl(imageDtl);

                adContents.add(content);
            }
        }

        return adContents;
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
    public static Uri getLocalBitmapUri(Bitmap bmp, Context activity) {
        Uri bmpUri = null;
        File file = new File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bmpUri = Uri.fromFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

}
