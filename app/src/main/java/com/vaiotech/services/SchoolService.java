package com.vaiotech.services;

import com.google.gson.internal.LinkedTreeMap;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.ArrayList;
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

        List<String> retVal = new ArrayList();
        List<LinkedTreeMap> list = (List<LinkedTreeMap>) getService().getSchools(cityID);
        if(list != null && !list.isEmpty()) {
            for(LinkedTreeMap currVal : list) {
                System.out.println("loadDataFromNetwork......................."+ currVal);
                retVal.add(currVal.get("schoolID") + " - " + currVal.get("schoolName"));
            }
        }
        System.out.println("loadDataFromNetwork.......................");
        return retVal;
    }
}
