package com.example.last_22.Banner;

public class NewBanner implements BannerData {
    private  String url;
    private  String title;

    public NewBanner(String title) {
        this.title = title;
    }

    @Override
    public String geturl() {
        return url;
    }

    @Override
    public String gettitle() {
        return title;
    }
}
