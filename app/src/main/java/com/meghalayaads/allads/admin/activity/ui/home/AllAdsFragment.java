package com.meghalayaads.allads.admin.activity.ui.home;

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

public class AllAdsFragment extends Fragment {

    private AllAdsFragmentViewModel allAdsFragmentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        allAdsFragmentViewModel =
                new ViewModelProvider(this).get(AllAdsFragmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_all_ads, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        allAdsFragmentViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}