package com.example.last_22.base.m;

import com.example.last_22.base.IBaseCallBack;
import com.example.last_22.data.net.request.MvpRequest;
import com.trello.rxlifecycle4.LifecycleProvider;

public interface IBaseMode {


    <T> void doRequest(LifecycleProvider lifecycleProvider, MvpRequest<T> request, IBaseCallBack<T> diBaseCallBack);
}
