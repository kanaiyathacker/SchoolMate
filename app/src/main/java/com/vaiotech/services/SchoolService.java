package com.vaiotech.services;

import com.google.gson.internal.LinkedTreeMap;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.List;

/**
 * Created by kanaiyalalt on 07/10/2014.
 */
public class SchoolService extends RetrofitSpiceRequest<List , RestServiceInterface> {

    String cityID;

    public SchoolService() {
        super(List.class, RestServiceInterface.class);
    }
    public SchoolService(String cityID) {
        super(List.class, RestServiceInterface.class);
        this.cityID = cityID;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    @Override
    public List loadDataFromNetwork() throws java.lang.Exception{
        System.out.println("loadDataFromNetwork.......................");
        System.out.println("loadDataFromNetwork......................." + getService().getCity());
        com.google.gson.internal.LinkedTreeMap map = (LinkedTreeMap) getService().getSchools(cityID);
        return null;
    }
}
