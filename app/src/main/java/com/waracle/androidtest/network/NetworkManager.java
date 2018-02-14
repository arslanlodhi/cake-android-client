package com.waracle.androidtest.network;
import java.util.ArrayList;
import java.util.HashMap;


public class NetworkManager implements NetworkCallBack{
    private static final String TAG = NetworkManager.class.getSimpleName();




    HashMap<String,NetworkCallProcess> queue=new HashMap<>();

    /**
     * Enqueue Server call and creates a network process
     * @param url String url
     * @param callBack callBack reference
     */
    public synchronized  void enqueue(String url, NetworkCallBack callBack){

        if(!queue.containsKey(url))
        {
            ArrayList<NetworkCallBack> callBacks=new ArrayList<>();
            callBacks.add(this);
            callBacks.add(callBack);

            NetworkCallProcess process=new NetworkCallProcess(callBacks);
            queue.put(url,process);
            process.execute(url);


        }else{
            queue.get(url).addNetworkCallBack(callBack);
        }

    }

    /**
     * Dequeue Server call and cancel if request is in progress
     * @param url String url
     */
    public void dequeueRequest(String url){
        if(queue.containsKey(url))
        {
            queue.get(url).clear();
            queue.remove(url);

        }
    }


    @Override
    public void onResponseReceived(String url, byte[] data, String charset) {

    }

    @Override
    public void onFailure(String url, String message) {

    }

    @Override
    public void onStart(String url) {

    }

    @Override
    public void onStop(String url) {
        dequeueRequest(url);
    }
}
