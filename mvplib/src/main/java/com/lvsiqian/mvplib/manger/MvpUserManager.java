package com.lvsiqian.mvplib.manger;

import android.util.Log;


import com.lvsiqian.mvplib.data.entity.IUser;
import com.lvsiqian.mvplib.utils.MvpSpUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MvpUserManager {
    private static IUser mUser;
    private static String mToken;
    private static long mExpireTime;

    private static final String SP_TOKEN = "token";
    private static final String SP_TOKEN_EXPIRE_TIME = "expire_time";

    private static List<WeakReference<IUserCallBack>> mCallBackList;



    public static void login(IUser user) {
        mUser = user;
        mToken = user.getTokenValue();
        mExpireTime = user.getExireTimeSeconds();

        // 发送一个广播 通知整个app 所有其他界面，告诉他们用户登录。

        if (mCallBackList != null) {
            for (WeakReference<IUserCallBack> weakReference : mCallBackList) {
                IUserCallBack  callBack = weakReference.get();
                if (callBack != null) {
                    MvpManger.postUI(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onUserLogin(user);
                        }
                    });

                }
            }
        }



        MvpSpUtils.saveApply(SP_TOKEN, user.getTokenValue());
        MvpSpUtils.saveApply(SP_TOKEN_EXPIRE_TIME, user.getExireTimeSeconds());

    }


    public static void logout() {
        mUser = null;
        mToken = null;
        mExpireTime = 0;
        MvpSpUtils.saveApply(SP_TOKEN, "");
        MvpSpUtils.saveApply(SP_TOKEN_EXPIRE_TIME, 0);

        if (mCallBackList != null) {
            IUserCallBack callBack;
            for (WeakReference<IUserCallBack> weakReference : mCallBackList) {
                callBack = weakReference.get();
                if (callBack != null) {
                    callBack.onUserLogout();
                }
            }
        }

    }


    public static <T extends IUser> IUserCallBack<T> registerUserStateCallBack(IUserCallBack<T> callBack) {

        if (mCallBackList == null) {
            mCallBackList = new ArrayList<>();
        }
        mCallBackList.add(new WeakReference<>(callBack));
        return callBack;
    }


    public static <T extends IUser> void unRegisterUserStateCallBack(IUserCallBack<T> callBack) {

        if (mCallBackList != null) {
            mCallBackList.remove(callBack);
            if (mCallBackList.size() == 0) {
                mCallBackList = null;
            }
        }
    }


    public static <T extends IUser> T getUser() {
        return (T) mUser;
    }


    public static String getToken() {
        long start = System.currentTimeMillis();
        if(mToken == null){
            mToken = MvpSpUtils.getString(SP_TOKEN);
            mExpireTime = MvpSpUtils.getLong(SP_TOKEN_EXPIRE_TIME);
        }

        Log.d("Test","------" + (System.currentTimeMillis()- start));

        if (System.currentTimeMillis() / 1000 < mExpireTime) {
            return  mToken;
        }
        return null;
    }


    public interface IUserCallBack<T extends IUser> {
        default void onUserLogin(T user) {

        }

        default void onUserUpdate(T user) {

        }

        default void onUserLogout() {

        }
    }

}
