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

    @GET("/myschoolWeeklyTimeTable/schoolID/{schoolID}/class/{class}/section/{section}/")
    Object getWeeklyTimeTable(@Path("schoolID") String schoolID , @Path("class") String className , @Path("section") String section);

    @GET("/myschoolMateDayTimeTable/schoolID/{schoolID}/class/{class}/section/{section}/day/{day}/")
    Object getDayTimeTable(@Path("schoolID") String schoolID , @Path("class") String className , @Path("section") String section , @Path("day") String day);

}
