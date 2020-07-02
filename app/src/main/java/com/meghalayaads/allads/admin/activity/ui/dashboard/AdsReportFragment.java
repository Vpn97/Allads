package com.meghalayaads.allads.admin.activity.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.meghalayaads.allads.R;


public class AdsReportFragment extends Fragment {

    private AdsReportFragmentViewModel adsReportFragmentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        adsReportFragmentViewModel =
                new ViewModelProvider(this).get(AdsReportFragmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ads_report_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        adsReportFragmentViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}