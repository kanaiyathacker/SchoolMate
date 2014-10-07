package com.vaiotech.services;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by kanaiyalalt on 07/10/2014.
 */
public class CityService extends RetrofitSpiceRequest<String , RestServiceInterface> {

    public CityService() {
        super(String.class, RestServiceInterface.class);
    }

    @Override
    public String loadDataFromNetwork() throws java.lang.Exception{
        System.out.println("loadDataFromNetwork......................." + getService().getCity());
        com.google.gson.internal.LinkedTreeMap map = (LinkedTreeMap) getService().getCity();

        return "Kanaiya,....";
    }

    public void getSchools(final String cityID , final String schoolID) {
        com.google.gson.internal.LinkedTreeMap map = (LinkedTreeMap) getService().getSchools(cityID , schoolID);
    }
}
