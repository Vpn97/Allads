package com.meghalayaads.allads.common.datasource;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.meghalayaads.allads.common.services.CommonServices;

/**
 * Allads
 * Created by Vishal Nagvadiya on 05-07-2020.
 */
public class AdsPaginationDataSourceFactory extends DataSource.Factory {

    private AdsPaginationDataSource dataSource;
    private Application application;
    private CommonServices services;
    private MutableLiveData<AdsPaginationDataSource> dataSourceMutableLiveData;

    public AdsPaginationDataSourceFactory(Application application,CommonServices services){
        this.application=application;
        this.services=services;
        this.dataSourceMutableLiveData=new MutableLiveData<>();
    }


    @NonNull
    @Override
    public AdsPaginationDataSource create() {
        dataSource=new AdsPaginationDataSource(services,application);
        dataSourceMutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<AdsPaginationDataSource> getDataSourceMutableLiveData() {
        return dataSourceMutableLiveData;
    }

}
