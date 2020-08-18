package com.example.last_22.auth.pwd;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.last_22.R;
import com.example.last_22.base.v.BaseActivity;
import com.example.last_22.data.entity.NewBanner;
import com.lvsiqian.mvplib.widgets.BottomNavigation;
import com.lvsiqianlib_banner.Banner;

import java.util.ArrayList;

public class AuthActivity   extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // MvpFragmentManager.addOrShowFragment(getSupportFragmentManager(),PasswordFragment.class,null,R.id.fl);

    }

    @Override
    protected void bindligView(View view) {

    }

    @Override
    protected void intView() {
       Banner banner = findViewById(R.id.banner);//找控件
        ArrayList<NewBanner> arrayList = new ArrayList<>();//初始化数据源
        for (int i = 0; i < 4; i++) {
            arrayList.add(new NewBanner("努力不一定成功，但放弃一定失败，趁着还年轻，珍惜每分每秒，加油！！！！"+i));
        }
        banner.setAdaPter(arrayList);//设置适配器
        BottomNavigation bottomNavigtion = findViewById(R.id.bottomNavigation);
        bottomNavigtion.addItem(R.drawable.tab_recommend_selector,"推荐列表")
                .addItem(R.drawable.tab_video_selector,"视频")
                .addItem(R.drawable.tab_special_selector,"专题")
                .addItem(R.drawable.tab_mine_selector,"我的")
                .apply();
        bottomNavigtion.setTabSelectedListener(new BottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelect(View tab, int position) {
                showToast("选中"+position);

            }

            @Override
            public void onTabUnSelect(View tab, int position) {
                showToast("取消选中"+position);
            }

            @Override
            public void onTabReSelected(View tab, int position) {
                    showToast("再次选中"+position);
            }
        });

    }

    @Override
    protected int getLayoutId() {
        //登录的页面
      // return R.layout.layout_auth_password;
        return R.layout.item_home_recommed_banner;
    }
}
