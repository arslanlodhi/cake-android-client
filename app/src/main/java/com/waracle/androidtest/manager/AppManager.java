package com.waracle.androidtest.manager;

import com.waracle.androidtest.network.NetworkManager;

/**
 * Created by arslanlodhi on 2/14/18.
 */

public class AppManager {

    private static AppManager appManager;

    public synchronized static AppManager getInstance(){
        if(appManager==null)
            appManager=new AppManager();
        return appManager;
    }

    NetworkManager networkManager=new NetworkManager();

    public NetworkManager getNetworkManager() {
        return networkManager;
    }
}
