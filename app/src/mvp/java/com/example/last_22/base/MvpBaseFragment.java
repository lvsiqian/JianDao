package com.example.last_22.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.last_22.base.p.IBasePresetener;
import com.example.last_22.base.v.IBaseView;
import com.example.last_22.widgets.MvpLoadingView;


public abstract class MvpBaseFragment<P extends IBasePresetener> extends BaseFragment implements IBaseView<P>, Baseview {

    protected P mPresenter;

    protected MvpLoadingView mLoadingView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresetener();
        mPresenter.bindView(this);

    }


    @Override
    public Context getMvpContent() {
        return getContext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.unbind();
    }

    @Override
    public IBasePresetener getPresenter() {
        return mPresenter;
    }

    @Override
    public void setLoadView(MvpLoadingView loadView) {
        mLoadingView = loadView;
    }

    @Override
    public MvpLoadingView getLoadingView() {
        return mLoadingView;
    }

}
