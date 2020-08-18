package com.example.last_22.data.net.ok;

import com.example.last_22.BuildConfig;
import com.example.last_22.data.net.ok.converter.MvpGsonConverterFactory;
import com.example.last_22.utils.Constrant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

public class DataService {
    private  static  final  long TIME_OUT=20000;
    public  static  volatile  ApiService mService;
    public   static  ApiService getService(){
        if(mService==null){
            synchronized (DataService.class){
                if(mService==null){
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                    if(BuildConfig.DEBUG){
                        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    }else{
                        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
                    }
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    builder.addInterceptor(loggingInterceptor);
                    builder.connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                            .writeTimeout(TIME_OUT,TimeUnit.MILLISECONDS)
                            .readTimeout(TIME_OUT,TimeUnit.MILLISECONDS)
                            .build();
                    Retrofit retrofit = new Retrofit.Builder()
                            .client(builder.build())
                            .addConverterFactory(MvpGsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                            .baseUrl(Constrant.BASE_URL)
                            .build();


                    mService=retrofit.create(ApiService.class);
                }
            }

        }
        return mService;
    }

}
