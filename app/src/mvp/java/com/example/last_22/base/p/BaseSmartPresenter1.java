package com.example.last_22.base.p;



import com.example.last_22.base.IBaseCallBack;
import com.example.last_22.base.m.IBaseMode;
import com.example.last_22.base.v.IBaseSmartView1;
import com.example.last_22.data.net.request.MvpRequest;
import com.example.last_22.data.net.response.BaseRepository;
import com.example.last_22.data.repository.MvpResponse;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class BaseSmartPresenter1<D,V extends IBaseSmartView1<D,?>> extends BasePresetener<V> implements IBaseSmartPresenter1<D,V> {

    protected IBaseMode mMode;
    protected CompositeDisposable mCompositeDisposable;
    protected Class<D> mType;

    @Override
    public void setType(Class<D> type) {
        mType = type;
    }

    public BaseSmartPresenter1() {
        mMode = new BaseRepository();
    }

    public BaseSmartPresenter1(IBaseMode baseMode) {
        mMode = baseMode;
    }

    public void doRequest(MvpRequest<D> request) {

        request.setType(mType);

        mMode.doRequest(getLifecycleProvider(),request, new IBaseCallBack<D>() {
            @Override
            public void onStart(Disposable disposable) {
               handStart(disposable);
            }

            @Override
            public void onResult(MvpResponse<D> response) {
                if(mView != null){
                    mView.onResult1(response);
                }
            }
        });
    }

    @Override
    public boolean cancelRequest() {
        if(mCompositeDisposable != null){
            mCompositeDisposable.dispose();
            return true;
        }

        return false;
    }

    protected  void handStart(Disposable disposable){
        if(mCompositeDisposable == null){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

}
