package com.vaiotech.services;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by kanaiyalalt on 02/10/2014.
 */
public class RestService extends RetrofitGsonSpiceService {
    private final static String BASE_URL = "http://api.geonames.org/citiesJSON";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(RestServiceInterface.class);
        System.out.println("RestService...");
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }
}
