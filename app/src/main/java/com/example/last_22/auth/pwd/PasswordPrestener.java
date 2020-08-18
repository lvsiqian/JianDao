package com.example.last_22.auth.pwd;

import com.example.last_22.base.IBaseCallBack;
import com.example.last_22.base.p.BasePresetener;
import com.example.last_22.data.entity.ParamsUtils;
import com.example.last_22.data.entity.User;
import com.example.last_22.data.net.request.PostRequest;
import com.example.last_22.data.repository.MvpResponse;
import com.example.last_22.utils.AppUtils;
import com.example.last_22.utils.Constrant;

import java.util.HashMap;

public class PasswordPrestener extends BasePresetener<PasswordContract.IPasswordView> implements  PasswordContract.IPwordPresetener {
   protected  PasswordContract.IPasswordLoginMode mModel;
   protected PasswordPrestener(){
       mModel=new PasswordRepository();
   }
    @Override
    public void Login(String username, String password) {
       if(!AppUtils.isValidUserCount(username)){
           mView.onInput("输入的手机号不正确");
           return;

       }
        if(!AppUtils.isValidUserPasssword(password)){
            mView.onInput("输入的密码不正确");
            return;

        }
        PostRequest<User> request = new PostRequest<>(Constrant.URL.LOGIN);
        HashMap<String, Object> commonParams = ParamsUtils.getCommonParams();
        commonParams.put(Constrant.RequestKey.KEY_USER_ACCOUNT,username);
        commonParams.put(Constrant.RequestKey.KEY_USER_PASSWORD,password);
        request.setParms(commonParams);
        mView.onShowLoading();
        mModel.login(getLifecycleProvider(),request, new IBaseCallBack<User>() {
            @Override
            public void onResult(MvpResponse<User> response) {
                mView.onCloseing();
                mView.Onsuccess(response);
            }
        });



    }
}
