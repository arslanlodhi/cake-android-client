package com.waracle.androidtest.network;


/**
 * Created by arslanlodhi on 2/13/18.
 */

public interface NetworkCallBack {

    public void onResponseReceived(String url, byte[] data, String charset);
    public  void onFailure(String url, String message);
    public  void onStart(String url);
    public  void onStop(String url);


}
