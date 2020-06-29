package com.meghalayaads.allads.common.util;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.meghalayaads.allads.R;

public class ViewUtil {

    //methid enable or disable all view that child of any VIewGroup
    public static void enableDisableView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if ( view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup)view;
            for ( int idx = 0 ; idx < group.getChildCount() ; idx++ ) {
                enableDisableView(group.getChildAt(idx), enabled);
            }
        }
    }


    public static PopupWindow getProgressDialLog(View root, LayoutInflater inflater){
        View popupView = inflater.inflate(R.layout.dailog_progressbar_layout, null);
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        PopupWindow window = new PopupWindow(popupView, width, height, true);
        window.showAtLocation(root, Gravity.CENTER, 0, 0);
        window.setAnimationStyle(R.style.Animation_Design_BottomSheetDialog);
        return window;
    }
}
