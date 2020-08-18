package com.example.last_22.base.v;


import com.example.last_22.base.p.IBaseSmartPresenter1;
import com.example.last_22.data.repository.MvpResponse;

public interface IBaseSmartView1<D,P extends IBaseSmartPresenter1> extends IBaseView<P> {
    void onResult1(MvpResponse<D> response);
}
