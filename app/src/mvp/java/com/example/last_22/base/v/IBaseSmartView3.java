package com.example.last_22.base.v;


import com.example.last_22.base.p.IBaseSmartPresenter3;
import com.example.last_22.data.repository.MvpResponse;

public interface IBaseSmartView3<D1,D2,D3,P extends IBaseSmartPresenter3<D1,D2,D3,?>> extends IBaseSmartView2<D1,D2,P> {
    void onResult3(MvpResponse<D3> response);
}
