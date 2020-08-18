package com.example.last_22.base.p;



import com.example.last_22.base.IBaseCallBack;
import com.example.last_22.base.m.IBaseMode;
import com.example.last_22.base.v.IBaseSmartView2;
import com.example.last_22.data.net.request.MvpRequest;
import com.example.last_22.data.repository.MvpResponse;

import io.reactivex.rxjava3.disposables.Disposable;

public class BaseSmartPresenter2<D1,D2,V extends IBaseSmartView2<D1,D2,?>> extends BaseSmartPresenter1<D1,V> implements IBaseSmartPresenter2<D1,D2,V> {

    protected Class<D2> mType2;

    public BaseSmartPresenter2() {
    }

    public BaseSmartPresenter2(IBaseMode baseMode) {
        super(baseMode);
    }

    @Override
    public void setType2(Class<D2> type) {
        mType2 = type;
    }

    @Override
    public void doRequest2(MvpRequest<D2> request) {
        mMode.doRequest(getLifecycleProvider(),request, new IBaseCallBack<D2>() {
            @Override
            public void onStart(Disposable disposable) {
               handStart(disposable);
            }
            @Override
            public void onResult(MvpResponse<D2> response) {
                if(mView != null){
                    mView.onResult2(response);
                }
            }
        });
    }

}
