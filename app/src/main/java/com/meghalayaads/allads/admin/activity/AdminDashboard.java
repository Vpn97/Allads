package com.meghalayaads.allads.admin.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.meghalayaads.allads.R;
import com.meghalayaads.allads.databinding.ActivityAdminDashboardBinding;

public class AdminDashboard extends AppCompatActivity {

    private ActivityAdminDashboardBinding mBinding;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        allocation();
        setEvent();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

    }

    private void allocation() {
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_admin_dashboard);
        appBarConfiguration= new AppBarConfiguration.Builder(
                R.id.nav_report, R.id.nav_all_ads, R.id.nav_settings)
                .build();

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.admin_nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(mBinding.bottomNavView, navController);
    }

    private void setEvent() {

    }

}