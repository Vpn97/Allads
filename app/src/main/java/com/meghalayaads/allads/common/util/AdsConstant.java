package com.meghalayaads.allads.common.util;

import com.meghalayaads.allads.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Allads
 * Created by Vishal Nagvadiya on 27-06-2020.
 */
public class AdsConstant {


    public static final String TAG = "ApkZube";
    public static final Integer PER_PAGE_ADS = 10;
    public static final Integer PER_PAGE_ADS_INIT = 10;





    public static Integer[] getColorList(int index){
        LinkedHashMap<Integer,Integer> hashMap=new LinkedHashMap<>();
        hashMap.put(R.color.back_red,R.color.text_red);
        hashMap.put(R.color.back_purpule,R.color.text_purpule);

        hashMap.put(R.color.back_green,R.color.text_green);
        hashMap.put(R.color.back_yellow,R.color.text_yellow);
        hashMap.put(R.color.back_orange,R.color.text_orange);
        hashMap.put(R.color.back_blue,R.color.text_blue);
        hashMap.put(R.color.back_brown,R.color.text_brown);
        hashMap.put(R.color.back_tint,R.color.text_tint);

        ArrayList<Integer[]> colors = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            Integer[]str = new Integer[2];
            str[0] = entry.getKey();
            str[1] = entry.getValue();
            colors.add(str);
        }

        return colors.get(index%hashMap.size());
    }

    public static int getRandomAvatar(){
        Integer[] res=new Integer[4];
        res[0]=R.mipmap.man;
        res[1]=R.mipmap.man_2;
        res[2]=R.mipmap.women;
        res[3]=R.mipmap.women_2;
        Random random = new Random();
        return res[random.nextInt(res.length)];
    }


}
