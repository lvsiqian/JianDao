package com.example.last_22.base.p;

import android.content.Context;

import com.example.last_22.base.v.IBaseView;
import com.trello.rxlifecycle4.LifecycleProvider;

public abstract class BasePresetener <V extends IBaseView> implements  IBasePresetener<V>{
    protected   V mView;

    @Override
    public void bindView(V view) {
        mView=view;

    }

    @Override
    public void unbind() {
        mView=null;

    }

    @Override
    public Context getMvpContent() {
        if(mView!=null){
            return  mView.getMvpContent();
        }
        return null;
    }

    @Override
    public boolean cancelRequest() {
        return false;
    }
    public LifecycleProvider getLifecycleProvider(){
        return (LifecycleProvider) mView;
    }
}
