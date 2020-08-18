package com.example.last_22.data.net.request;

import java.util.HashMap;

public class MvpRequest<T> {
    protected  String url;
    protected RequestType requestType=RequestType.FIRST;
    protected  RequestMethod requestMethod;
    protected HashMap<String,Object> parms;
    protected HashMap<String,Object> heads;
    private  Class<T> type;
    protected  boolean isEnableCancel;
    public   MvpRequest(){

    }
    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }
    public   MvpRequest(String url){
        this.url=url;

    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public HashMap<String, Object> getParms() {
        return parms;
    }

    public void setParms(HashMap<String, Object> parms) {
        this.parms = parms;
    }

    public HashMap<String, Object> getHeads() {
        return heads== null ? new HashMap<>() : heads;
    }

    public void setHeads(HashMap<String, Object> heads) {
        this.heads = heads;
    }



    public boolean isEnableCancel() {
        return isEnableCancel;
    }

    public void setEnableCancel(boolean enableCancel) {
        isEnableCancel = enableCancel;
    }
}
