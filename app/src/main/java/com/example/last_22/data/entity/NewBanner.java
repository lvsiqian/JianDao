package com.example.last_22.data.entity;

import com.lvsiqianlib_banner.BannerData;

public class NewBanner implements BannerData {
    private  String url;
    private String title;


    public NewBanner(String title) {
        this.title = title;
    }

    @Override
    public String getitle() {
        return title;
    }

    @Override
    public String geturl() {
        return url;
    }
}
