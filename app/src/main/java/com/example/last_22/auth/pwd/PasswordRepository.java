package com.example.last_22.auth.pwd;

import com.example.last_22.base.IBaseCallBack;
import com.example.last_22.data.entity.User;
import com.example.last_22.data.net.request.MvpRequest;
import com.example.last_22.data.net.response.BaseRepository;
import com.example.last_22.data.repository.MvpResponse;
import com.lvsiqian.mvplib.manger.MvpUserManager;
import com.trello.rxlifecycle4.LifecycleProvider;

import io.reactivex.rxjava3.functions.Consumer;

public class PasswordRepository  extends BaseRepository implements PasswordContract.IPasswordLoginMode {
    @Override
    public void login(LifecycleProvider provider,MvpRequest<User> request, IBaseCallBack<User> back) {

        doRequest(provider,request, new Consumer<MvpResponse<User>>() {
            @Override
            public void accept(MvpResponse<User> userMvpResponse) throws Throwable {

                if(userMvpResponse.isOk()){
                    User user = userMvpResponse.getData();
                    MvpUserManager.login(user);

                }
            }
        },back);


    }

}
