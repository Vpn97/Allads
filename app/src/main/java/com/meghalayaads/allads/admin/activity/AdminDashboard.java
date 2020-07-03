package com.meghalayaads.allads.admin.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.meghalayaads.allads.R;
import com.meghalayaads.allads.admin.model.AdminMst;
import com.meghalayaads.allads.common.util.DataStorage;
import com.meghalayaads.allads.databinding.ActivityAdminDashboardBinding;

public class AdminDashboard extends AppCompatActivity {

    private ActivityAdminDashboardBinding mBinding;
    private AppBarConfiguration appBarConfiguration;
    private AdminMst adminMst;
    private DataStorage storage;

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
        storage=new DataStorage(this,getString(R.string.key_user_data));
        adminMst =new Gson().fromJson(String.valueOf(storage.read(getString(R.string.key_admin_mst), DataStorage.STRING)),AdminMst.class);

                // mBinding= DataBindingUtil.setContentView(this,R.layout.activity_admin_dashboard);
                BottomNavigationView navView = findViewById(R.id.bottom_nav_view);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_report, R.id.nav_all_ads, R.id.nav_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.admin_nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        getSupportActionBar().setTitle("Welcome "+adminMst.getMobNo());
    }

    private void setEvent() {

    }

}