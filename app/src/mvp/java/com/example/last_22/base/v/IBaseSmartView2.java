package com.example.last_22.base.v;


import com.example.last_22.base.p.IBaseSmartPresenter2;
import com.example.last_22.data.repository.MvpResponse;

public interface IBaseSmartView2<D1,D2,P extends IBaseSmartPresenter2<D1,D2,?>> extends IBaseSmartView1<D1,P> {
    void onResult2(MvpResponse<D2> response);
}
