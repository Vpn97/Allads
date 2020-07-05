package com.meghalayaads.allads.admin.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
import com.meghalayaads.allads.user.activity.registration.ActivityLogin;

public class AdminDashboard extends AppCompatActivity {

    private ActivityAdminDashboardBinding mBinding;
    private AppBarConfiguration appBarConfiguration;
    private AdminMst adminMst;
    private DataStorage storage;
    NavController navController;

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
        navController = Navigation.findNavController(this, R.id.admin_nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        getSupportActionBar().setTitle("Welcome "+adminMst.getMobNo());
    }

    private void setEvent() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.menu_admin_logout){


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.are_you_sure_logout));

            builder.setPositiveButton(getString(R.string.logout), (dialog, which) -> {
                SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences(getResources().getString(R.string.key_user_data), 0).edit();
                editor.clear();
                editor.commit();

                Intent intent=new Intent(getApplicationContext(), ActivityLogin.class);
                startActivity(intent);
                finish();
            });

            builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> {
                builder.create().dismiss();
            });
            builder.create().show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}