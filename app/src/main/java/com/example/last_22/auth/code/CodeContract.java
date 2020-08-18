package com.example.last_22.auth.code;

import com.example.last_22.base.IBaseCallBack;
import com.example.last_22.base.p.IBasePresetener;
import com.example.last_22.base.v.IBaseView;
import com.example.last_22.data.entity.Code;
import com.example.last_22.data.entity.Codeuser;
import com.example.last_22.data.net.request.MvpRequest;
import com.example.last_22.data.repository.MvpResponse;
import com.trello.rxlifecycle4.LifecycleProvider;

public interface CodeContract {
    interface  IcodePresetener extends IBasePresetener<Icodeview>{
        void getCode(String usrname);//验证码
        void PriLogin(String username,String code);//验证码登录
    }
    interface  Icodeview extends IBaseView<IcodePresetener> {
        void OnCodeSuccess(MvpResponse<Code> response);//验证码
        void OnUserResult(MvpResponse<Codeuser> response);//验证码登录
        void InputError(String massage);
        void ShowLoading();
        void CloseLoading();

    }
    interface  Icodemodel{
        void getCode(LifecycleProvider provider,MvpRequest<Code> request, IBaseCallBack<Code> callBack);
        void PriLogin(LifecycleProvider provider,MvpRequest<Codeuser> request, IBaseCallBack<Codeuser> callBack);

    }
}
