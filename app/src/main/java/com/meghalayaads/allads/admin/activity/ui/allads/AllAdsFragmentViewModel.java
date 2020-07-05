package com.meghalayaads.allads.admin.activity.ui.allads;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.meghalayaads.allads.common.datasource.AdsPaginationDataSource;
import com.meghalayaads.allads.common.datasource.AdsPaginationDataSourceFactory;
import com.meghalayaads.allads.common.model.AdMst;
import com.meghalayaads.allads.common.services.CommonServices;
import com.meghalayaads.allads.common.services.CommonServicesImpl;
import com.meghalayaads.allads.common.util.AdsConstant;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AllAdsFragmentViewModel extends AndroidViewModel {

    private Application application;
    private LiveData<PagedList<AdMst>> adMstPagedList;
    private LiveData<AdsPaginationDataSource> liveDataSource;
    private Executor executor;
    private AdsPaginationDataSourceFactory factory;
    private PagedList.Config config;
    private CommonServices services;

    public AllAdsFragmentViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
        this.services= CommonServicesImpl.getService();
        this.factory=new AdsPaginationDataSourceFactory(application,services);
        liveDataSource=factory.getDataSourceMutableLiveData();


        this.config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(AdsConstant.PER_PAGE_ADS_INIT)
                .setPageSize(AdsConstant.PER_PAGE_ADS)
                .setPrefetchDistance(4)
                .build();
        this.executor = Executors.newFixedThreadPool(5);
        adMstPagedList=(new LivePagedListBuilder<Integer,AdMst>(factory,config)
        .setFetchExecutor(executor)
        .build());
    }


    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public LiveData<PagedList<AdMst>> getAdMstPagedList() {
        return adMstPagedList;
    }


}