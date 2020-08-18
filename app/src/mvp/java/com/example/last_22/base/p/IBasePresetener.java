package com.example.last_22.base.p;

import android.content.Context;

import com.example.last_22.base.v.IBaseView;

public interface IBasePresetener<V extends IBaseView> {
    void bindView(V view);
    void unbind();
    Context getMvpContent();
    boolean cancelRequest();
}
