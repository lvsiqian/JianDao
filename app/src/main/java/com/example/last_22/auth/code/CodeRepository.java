package com.example.last_22.auth.code;

import com.example.last_22.base.IBaseCallBack;
import com.example.last_22.data.entity.Code;
import com.example.last_22.data.entity.Codeuser;
import com.example.last_22.data.net.request.MvpRequest;
import com.example.last_22.data.net.response.BaseRepository;
import com.example.last_22.data.repository.MvpResponse;
import com.lvsiqian.mvplib.manger.MvpUserManager;
import com.trello.rxlifecycle4.LifecycleProvider;

import io.reactivex.rxjava3.functions.Consumer;

public class CodeRepository  extends BaseRepository implements  CodeContract.Icodemodel{




    @Override
    public void getCode(LifecycleProvider provider,MvpRequest<Code> request, IBaseCallBack<Code> callBack) {
        doRequest(provider,request, new Consumer<MvpResponse<Code>>() {

            @Override
            public void accept(MvpResponse<Code> userMvpResponse) throws Throwable {
                if(userMvpResponse.isOk()){
                    Code data = userMvpResponse.getData();
                    //MvpUserManager.login(data);

                }
            }
        },callBack);
    }

    @Override
    public void PriLogin(LifecycleProvider provider,MvpRequest<Codeuser> request, IBaseCallBack<Codeuser> callBack) {
        doRequest(provider,request, new Consumer<MvpResponse<Codeuser>>() {

            @Override
            public void accept(MvpResponse<Codeuser> userMvpResponse) throws Throwable {
                if(userMvpResponse.isOk()){
                    Codeuser data = userMvpResponse.getData();
                    MvpUserManager.login(data);

                }
            }
        },callBack);
    }

}
