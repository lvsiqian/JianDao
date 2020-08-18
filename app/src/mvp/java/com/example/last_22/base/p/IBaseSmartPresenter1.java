package com.example.last_22.base.p;


import com.example.last_22.base.v.IBaseSmartView1;
import com.example.last_22.data.net.request.MvpRequest;

public interface IBaseSmartPresenter1<D,V extends IBaseSmartView1<D,?>> extends IBasePresetener<V>{
    void setType(Class<D> type);
    void doRequest(MvpRequest<D> request);



}
