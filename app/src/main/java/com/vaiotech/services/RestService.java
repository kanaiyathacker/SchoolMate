package com.vaiotech.services;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by kanaiyalalt on 02/10/2014.
 */
public class RestService extends RetrofitGsonSpiceService {
    private final static String BASE_URL = "http://devicetokenapplicai-env.elasticbeanstalk.com/myschool/";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(RestServiceInterface.class);
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }
}
