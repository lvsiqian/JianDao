package com.example.last_22.base.v;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.last_22.base.Baseview;
import com.example.last_22.base.p.IBasePresetener;
import com.example.last_22.widgets.MvpLoadingView;

public abstract  class MvpBaseActivity  <P extends IBasePresetener> extends  BaseActivity implements Baseview,IBaseView<P> {
    private MvpLoadingView mvpLoadingView;
    protected  P mPresetener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresetener=createPresetener();
        mPresetener.bindView(this);
        loadData();
    }

    protected abstract void loadData();
    @Override
    public void setLoadView(MvpLoadingView loadView) {
        mvpLoadingView = loadView;
    }

    @Override
    public MvpLoadingView getLoadingView() {
        return mvpLoadingView;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresetener.unbind();
    }
}
