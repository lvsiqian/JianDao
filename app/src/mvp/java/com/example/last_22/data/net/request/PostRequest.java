package com.example.last_22.data.net.request;

public class PostRequest<T> extends  MvpRequest<T> {
    public PostRequest() {
    }

    public PostRequest(String url) {
        super(url);
    }
    public  RequestMethod getRequestMethod(){
        return  RequestMethod.POST;
    }
}
