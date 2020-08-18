package com.example.last_22.base;

import com.example.last_22.data.net.request.MvpRequest;
import com.example.last_22.data.repository.MvpResponse;

import io.reactivex.rxjava3.disposables.Disposable;


public   interface IBaseCallBack<T> {
    void onResult(MvpResponse<T> response);
    default  void onStart(Disposable disposable){

    }

}
