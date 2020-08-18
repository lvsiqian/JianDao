package com.example.last_22.home;

import androidx.annotation.RequiresApi;
import androidx.viewbinding.ViewBinding;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.last_22.R;
import com.example.last_22.base.v.BaseActivity;
import com.example.last_22.data.entity.ParamsUtils;
import com.example.last_22.data.entity.User;
import com.example.last_22.data.net.request.GetRequest;
import com.example.last_22.data.net.response.BaseRepository;
import com.example.last_22.data.repository.MvpResponse;
import com.example.last_22.databinding.ActivitySplashBinding;
import com.example.last_22.utils.Constrant;
import com.lvsiqian.mvplib.manger.MvpManger;
import com.lvsiqian.mvplib.manger.MvpUserManager;
import com.lvsiqian.mvplib.widgets.MvpCommonPopView;
import com.m.k.systemui.SystemBarConfig;

import io.reactivex.rxjava3.functions.Consumer;

public class SplashActivity extends BaseActivity {
//    ActivitySplashBinding bing
//            ;
    private  int SPLASH_TIME=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MvpManger.isShowGuidePage()){//第一次安装升级或者清除数据
            MvpCommonPopView mvpCommonPopView = new MvpCommonPopView(this);
            mvpCommonPopView.setContentView(View.inflate(this,R.layout.layout_splash_pop,null));
            mvpCommonPopView.setOnBackKeyDismiss(false);
        AgregreementPop agregreementPop = new AgregreementPop(this);
        agregreementPop.setClickListener(new AgregreementPop.IPopClickListener() {
            @Override
            public void onCancel() {
                //关闭当前activity
                MvpManger.launchFail();
                finish();

            }

            @Override
            public void onAgree() {
                //显示引导页
                agregreementPop.dismiss();
                showGuidePage();

            }

            @Override
            public void onUserAgrement() {

            }

            @Override
            public void onPrivaryPolicy() {

            }
        });
      //  agregreementPop.showCenter(getWindow().getDecorView());

        getWindow().getDecorView().post(new Runnable() {
                @Override
                public void run() {
                    agregreementPop.showCenter(getWindow().getDecorView());
                }
            });


        }else{
            // 设置为全屏
            SystemBarConfig config = new SystemBarConfig(this).enterFullScreen(SystemBarConfig.MODE_HIDE_LEAN_BACK);
            config.apply();
            getWindow().getDecorView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //3秒跳转
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();

                }
            },SPLASH_TIME);

        }

    }

    @Override
    protected void bindligView(View view) {

    }

    private void showGuidePage(){
        setContentView(R.layout.activity_splash);
        //startActivity(new Intent(this, AuthActivity.class));

    }
    @Override
    protected void intView() {


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }



    //获取用户信息
    @RequiresApi(api = Build.VERSION_CODES.N)
    private  void getUserInfo() {
        String token = MvpUserManager.getToken();
        if (!TextUtils.isEmpty(token)) {
            return;
        }
        GetRequest<User> userQuest = new GetRequest<>(Constrant.URL.GET_USER);
        userQuest.setParms(ParamsUtils.getCommonParams());
        userQuest.getParms().put(Constrant.RequestKey.KEY_TOKEN, token);
        userQuest.setType(User.class);


//  new BaseRepository().doRequest(null, userRequest, new Consumer<MvpResponse<User>>() {
//            @Override
//            public void accept(MvpResponse<User> response) throws Throwable {
//                if (response.isOk()) {
//                    MvpUserManager.login(response.getData());
//                }
//            }
//        }, new NoResultCallBack<>());
//
//    }


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
        }

//    @Override
//    protected void bindligView(View view) {
//      bing=ActivitySplashBinding.bind(view);
//    }
}