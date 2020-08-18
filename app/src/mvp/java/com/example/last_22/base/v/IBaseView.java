package com.example.last_22.base.v;

import android.content.Context;

import com.example.last_22.base.p.IBasePresetener;

public interface IBaseView< P extends IBasePresetener>  {
    P  createPresetener();
    Context getMvpContent();
}
