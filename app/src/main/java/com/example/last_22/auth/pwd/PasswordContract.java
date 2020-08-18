package com.example.last_22.auth.pwd;

import com.example.last_22.base.IBaseCallBack;
import com.example.last_22.base.p.IBasePresetener;
import com.example.last_22.base.v.IBaseView;
import com.example.last_22.data.entity.User;
import com.example.last_22.data.net.request.MvpRequest;
import com.example.last_22.data.repository.MvpResponse;
import com.trello.rxlifecycle4.LifecycleProvider;

public interface PasswordContract {
    interface  IPasswordView extends IBaseView<IPwordPresetener>{
        void Onsuccess(MvpResponse<User> response);
        void onShowLoading();
        void  onCloseing();
        void onInput(String msg);

    }
    interface  IPwordPresetener extends IBasePresetener<IPasswordView> {
        void Login(String username,String  password);

    }
    interface  IPasswordLoginMode{
        void login(LifecycleProvider provider, MvpRequest<User> request, IBaseCallBack<User> back);
    }
}
