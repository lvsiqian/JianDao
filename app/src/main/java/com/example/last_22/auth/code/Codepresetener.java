package com.example.last_22.auth.code;

import com.example.last_22.base.IBaseCallBack;
import com.example.last_22.base.p.BasePresetener;
import com.example.last_22.data.entity.Code;
import com.example.last_22.data.entity.Codeuser;
import com.example.last_22.data.entity.ParamsUtils;
import com.example.last_22.data.net.request.PostRequest;
import com.example.last_22.data.repository.MvpResponse;
import com.example.last_22.utils.Constrant;

import java.util.HashMap;

public class Codepresetener extends BasePresetener<CodeContract.Icodeview> implements CodeContract.IcodePresetener {

    private  CodeContract.Icodemodel mModel;
    private  Codepresetener(){
        mModel=new CodeRepository();
    }
    @Override
    public void getCode(String usrname) {
        PostRequest<Code> request = new PostRequest<>(Constrant.URL.Pri);
        HashMap<String, Object> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("username",usrname);
        commonParams.put("type",1);
        request.setParms(commonParams);
        mView.ShowLoading();
        mModel.getCode(getLifecycleProvider(),request, new IBaseCallBack<Code>() {
            @Override
            public void onResult(MvpResponse<Code> response) {
                mView.CloseLoading();
                mView.OnCodeSuccess(response);
            }
        });

    }

    @Override
    public void PriLogin(String username, String code) {

    }

//    @Override
//    public void PriLogin(String username, String code) {
//        PostRequest<Codeuser> request = new PostRequest<>(Constrant.URL.PriLogin);
//        HashMap<String, Object> commonParams = ParamsUtils.getCommonParams();
//        commonParams.put("username",username);
//        commonParams.put("code",code);
//        request.setParms(commonParams);
//        mView.ShowLoading();
//        mModel.PriLogin(request, new IBaseCallBack<Codeuser>() {
//            @Override
//            public void onResult(MvpResponse<Codeuser> response) {
//                mView.CloseLoading();
//
//
//            }
//        })
//
//    }
}
