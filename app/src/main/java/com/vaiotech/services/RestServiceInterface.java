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

    @GET("/myschool/myschoolID/{schoolID}/modelID/{modelID}")
    Object getModelDesc(@Path("schoolID") String schoolID , @Path("modelID") String modelID );

    @GET("/weekly/schoolID/{schoolID}/className/{className}/section/{section}/")
    Object getWeeklyTimeTable(@Path("schoolID") String schoolID , @Path("className") String className , @Path("section") String section);

    @GET("/day/{day}/schoolID/{schoolID}/className/{className}/section/{section}/")
    Object getDayTimeTable(@Path("day") String day , @Path("schoolID") String schoolID , @Path("className") String className , @Path("section") String section);
}
