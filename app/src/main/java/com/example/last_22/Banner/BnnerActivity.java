package com.example.last_22.Banner;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.last_22.R;
import com.example.last_22.base.v.BaseActivity;

import java.util.ArrayList;

public class BnnerActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void bindligView(View view) {

    }

    @Override
    protected void intView() {
        Banner banner = findViewById(R.id.banner);
        ArrayList<NewBanner> arrayList = new ArrayList<>();
        for (int i = 0; i <4; i++) {
            arrayList.add(new NewBanner("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈红红火火恍恍惚惚红红火火恍恍惚惚哈哈哈哈"+i));

        }
        banner.setAdaPter(arrayList);//设置适配器
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bnner;
    }


}