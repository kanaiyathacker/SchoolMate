package com.vaiotech.services;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by kanaiyalalt on 02/10/2014.
 */
public interface RestServiceInterface {
    @GET("/")
    Object getDescription();

    @GET("/")
    Object getCity();

    @GET("/myschoolID/{cityID}/modelID/{modelID}")
    Object getSchools(String cityID , String modelID);
}
