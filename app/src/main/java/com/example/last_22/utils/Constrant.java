package com.example.last_22.utils;

public interface Constrant {


   String BASE_URL = "https://www.seetao.com";
   String VALUE_FROM = "ios";
   String VALUE_LANG = "zh";



   interface  URL{

       String LOGIN = "app/v_1_7_2/user/login2";

       //https://www.seetao.com/app/v_1_7_2/captcha/getcaptcha
       String Pri="app/v_1_7_2/captcha/getcaptcha/getcaptcha";//获取验证码
       //https://www.seetao.com/app/v_1_7_2/user/login//验证码登录
       String PriLogin="app/v_1_7_2/user/login";
       String GET_USER = "/api/user/getuserinfo";//获取用户信息
       String COLUMN_MANAGER = "/api/column/columnmanagelist";
   }


    interface  RequestKey{


      String KEY_FROM = "from";
      String KEY_LANG = "lang";
      String KEY_TIMESTAMP = "timestamp";
      String KEY_NONCE = "nonce";
      String KEY_SIGNATURE = "signature";


      String KEY_USER_ACCOUNT = "username";
      String KEY_USER_PASSWORD = "password";
      String KEY_USER_CAPTCHA = "captcha";

        String KEY_CODE = "code";
        String KEY_TOKEN = "token";



   }
}
