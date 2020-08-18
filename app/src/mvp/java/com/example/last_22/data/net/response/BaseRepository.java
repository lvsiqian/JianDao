package com.example.last_22.data.net.response;

import com.example.last_22.R;
import com.example.last_22.base.IBaseCallBack;
import com.example.last_22.base.m.IBaseMode;
import com.example.last_22.data.entity.HttpResult;
import com.example.last_22.data.net.ok.DataService;
import com.example.last_22.data.net.request.MvpRequest;
import com.example.last_22.data.repository.MvpResponse;
import com.example.last_22.utils.ParameterizedTypeImpl;
import com.google.gson.Gson;
import com.trello.rxlifecycle4.LifecycleProvider;
import com.trello.rxlifecycle4.RxLifecycle;
import com.trello.rxlifecycle4.android.ActivityEvent;
import com.trello.rxlifecycle4.android.FragmentEvent;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class BaseRepository implements IBaseMode {

    public Consumer empty = o -> {
    };

    @Override
    public <T> void doRequest(LifecycleProvider lifecycleProvider,MvpRequest<T> request, IBaseCallBack<T> callBack) {
        doRequest(lifecycleProvider,request, empty, callBack);
    }

    protected <T> void doRequest(LifecycleProvider lifecycleProvider,MvpRequest<T> request, Consumer<MvpResponse<T>> doBackground, IBaseCallBack<T> callBack) {
        switch (request.getRequestMethod()) {
            case GET:
                doObserver(lifecycleProvider,request, DataService.getService().doGet(request.getUrl(), request.getHeads(), request.getParms()), doBackground, callBack);

                break;
            case POST:
                doObserver(lifecycleProvider,request, DataService.getService().doPost(request.getUrl(), request.getHeads(), request.getParms()), doBackground, callBack);
                break;
        }
    }

    @SuppressWarnings("ALL")
    protected  <T> void doObserver(LifecycleProvider lifecycleProvider, MvpRequest<T> request, Observable<String> observable, Consumer<MvpResponse<T>> consumer, IBaseCallBack<T> callBack) {


        if (request.getType() == null && callBack != null) {


            Type[] interfaces = callBack.getClass().getGenericInterfaces();

            ParameterizedType parameterizedType = (ParameterizedType) interfaces[0];

            request.setType((Class<T>) parameterizedType.getActualTypeArguments()[0]);

        }
        Observable<MvpResponse<T>> observable1 =   observable.map(json2Data(request))
                .doOnNext(consumer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


        if(lifecycleProvider != null){

            if(lifecycleProvider instanceof RxAppCompatActivity){
                observable1 = observable1.compose(RxLifecycle.bindUntilEvent(lifecycleProvider.lifecycle(), ActivityEvent.DESTROY));
            }else{
                observable1 =  observable1.compose(RxLifecycle.bindUntilEvent(lifecycleProvider.lifecycle(), FragmentEvent.DESTROY));
            }
        }
        observable1.subscribe(new Observer<MvpResponse<T>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                if(request.isEnableCancel() && callBack != null){
                    callBack.onStart(d);
                }
            }

            @Override
            public void onNext(@NonNull MvpResponse<T> data) {

                if(callBack != null)
                    callBack.onResult(data);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                if(callBack != null)
                    callBack.onResult(new MvpResponse<T>().message(e.getMessage()));
            }

            @Override
            public void onComplete() {

            }
        });
    }

        public <T> Function<String, MvpResponse<T>> json2Data(MvpRequest<T> request) {
        return new Function<String, MvpResponse<T>>() {
            @Override
            public MvpResponse<T> apply(String s) throws Throwable {

                // Thread.sleep(20 * 1000);
                // IBaseCallBack<ColumnData>

                /*Type[] types = callBack.getClass().getGenericInterfaces();
                ParameterizedType parameterizedType = (ParameterizedType) types[0];
                Type realType = parameterizedType.getActualTypeArguments()[0];
                */

                //HttpResult<ColumnData>
                Gson gson = new Gson();
                ParameterizedTypeImpl type = new ParameterizedTypeImpl(HttpResult.class, new Type[]{request.getType()});
                HttpResult<T> data = gson.fromJson(s, type);
                if (data.getCode() == 1) {
                    if (data.getData() != null) {
                        return new MvpResponse<T>().setData(data.getData()).setCode(data.getCode());
                    } else {
                        return new MvpResponse<T>().setCode(data.getCode()).message("服务器异常");
                    }
                } else {
                    return new MvpResponse<T>().setCode(data.getCode()).message(data.getMessage());
                }
            }
        };
    }
}
