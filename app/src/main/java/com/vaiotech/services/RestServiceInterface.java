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

    @GET("/myschoolTimeTable/weekly/schoolID/{schoolID}/className/{className}/section/{section}/")
    Object getWeeklyTimeTable(@Path("schoolID") String schoolID , @Path("className") String className , @Path("section") String section);

    @GET("/myschoolTimeTable/day/{day}/schoolID/{schoolID}/className/{className}/section/{section}/")
    Object getDayTimeTable(@Path("day") String day , @Path("schoolID") String schoolID , @Path("className") String className , @Path("section") String section);

    @GET("/schoolMate/facultyInfo/{schoolID}/{className}/{section}")
    Object getFacultyInfo(@Path("schoolID") String schoolID , @Path("className") String className , @Path("section") String section);

    @GET("/myschool/aggregateTermResults/{studentID}/{schoolID}/{className}/{section}")
    Object getStudentResults(@Path("studentID") String studentID , @Path("schoolID") String schoolID , @Path("className") String className , @Path("section") String section);

    @GET("/myschool/getStudentTermResults/{studentID}/{schoolID}/{className}/{section}/{term}")
    Object getStudentTermResults(String studentID, String schoolID, String className, String section, String term);
}
