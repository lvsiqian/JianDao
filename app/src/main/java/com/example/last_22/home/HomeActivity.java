package com.example.last_22.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import android.os.Bundle;
import android.view.View;

import com.example.last_22.R;
import com.example.last_22.base.v.BaseActivity;
import com.example.last_22.databinding.ActivityHomeBinding;
import com.example.last_22.home.Recommend.RecommendFragment;
import com.example.last_22.manger.MvpFragmentManager;
import com.lvsiqian.mvplib.widgets.BottomNavigation;

public class HomeActivity extends BaseActivity {
    private  ActivityHomeBinding binding;


    @Override
    protected void intView() {
        binding.bottomNavigation
                .addItem(R.drawable.tab_recommend_selector,getString(R.string.text_tab_recommend))
                .addItem(R.drawable.tab_video_selector,getString(R.string.text_tab_video))
                .addItem(R.drawable.tab_special_selector,getString(R.string.text_tab_special))
                .addItem(R.drawable.tab_mine_selector,getString(R.string.text_tab_mime))
                .apply();

        binding.bottomNavigation.setTabSelectedListener(new BottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelect(View tab, int position) {
                MvpFragmentManager.addOrShowFragment(getSupportFragmentManager(), RecommendFragment.class,null,R.id.fragmentContainer);
            }

            @Override
            public void onTabUnSelect(View tab, int position) {

            }

            @Override
            public void onTabReSelected(View tab, int position) {

            }
        });


        // binding.drawerLayout.openDrawer(Gravity.LEFT);



    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }


    protected void bindligView(View view) {
        binding=ActivityHomeBinding.bind(view);
    }
}