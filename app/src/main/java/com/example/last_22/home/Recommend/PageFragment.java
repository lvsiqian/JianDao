package com.example.last_22.home.Recommend;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.last_22.R;
import com.example.last_22.base.p.BaseSmartPresenter1;
import com.example.last_22.base.v.BaseSmartFragment1;
import com.example.last_22.data.entity.User;
import com.example.last_22.data.repository.MvpResponse;

public class PageFragment extends BaseSmartFragment1<User> {
    private static final String KEY =  "columnId";

    private String mColumnId;

    public static PageFragment newInstance(String columnId){

        PageFragment pageFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY,columnId);
        pageFragment.setArguments(bundle);

        return pageFragment;
    }


    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);

        if(args != null){
            mColumnId = args.getString(KEY);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_test;
    }

    @Override
    public void onResult1(MvpResponse response) {

    }

}
