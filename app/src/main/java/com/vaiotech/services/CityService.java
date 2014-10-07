package com.vaiotech.services;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanaiyalalt on 07/10/2014.
 */
public class CityService extends RetrofitSpiceRequest<List , RestServiceInterface> {

    public CityService() {
        super(List.class, RestServiceInterface.class);
    }

    @Override
    public List<String> loadDataFromNetwork() throws java.lang.Exception{
        List<String> retVal = new ArrayList();
        List<LinkedTreeMap> list = (List<LinkedTreeMap>) getService().getCity();
        if(list != null && !list.isEmpty()) {
            for(LinkedTreeMap currVal : list) {
                retVal.add(currVal.get("cityID") + " - " + currVal.get("cityName"));
            }
        }
        return retVal;
    }
}
