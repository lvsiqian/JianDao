package com.example.last_22.data.net.request;

public class GetRequest<T> extends  MvpRequest<T> {
    public GetRequest() {
    }

    public GetRequest(String url) {
        super(url);
    }
    public  RequestMethod getRequestMethod(){
        return  RequestMethod.GET;
    }
}
