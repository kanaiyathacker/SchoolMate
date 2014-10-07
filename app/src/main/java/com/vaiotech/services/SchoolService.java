package com.vaiotech.services;

import com.google.gson.internal.LinkedTreeMap;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

/**
 * Created by kanaiyalalt on 07/10/2014.
 */
public class SchoolService extends RetrofitSpiceRequest<String , RestServiceInterface> {

    String cityID;
    String schoolID;

    public SchoolService(String cityID , String schoolID) {
        super(String.class, RestServiceInterface.class);
        this.cityID = cityID;
        this.schoolID = schoolID;
    }

    @Override
    public String loadDataFromNetwork() throws java.lang.Exception{
        System.out.println("loadDataFromNetwork......................." + getService().getCity());
        com.google.gson.internal.LinkedTreeMap map = (LinkedTreeMap) getService().getSchools(cityID , schoolID);
        return "Kanaiya,....";
    }
}
