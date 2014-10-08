package com.vaiotech.services;

import retrofit.http.GET;
import retrofit.http.*;

/**
 * Created by kanaiyalalt on 02/10/2014.
 */
public interface RestServiceInterface {
    @GET("/")
    Object getDescription();

    @GET("/city/getAllCity")
    Object getCity();

    @GET("/citySchool/cityId/{cityID}")
    Object getSchools(@Path("cityID") String cityID);
}
