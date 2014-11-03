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

    @GET("/myschool/termResult/{studentID}/{schoolID}/{className}/{section}/{term}")
    Object getStudentTermResult(@Path("studentID") String studentID , @Path("schoolID") String schoolID , @Path("className") String className , @Path("section") String section , @Path("term") String term);

    @GET("/myschool/getStudentScoreResult/{studentID}/{schoolID}/{className}/{section}/{term}/{sub}")
    Object getStudentScoreResult(@Path("studentID") String studentID , @Path("schoolID") String schoolID , @Path("className") String className , @Path("section") String section , @Path("term") String term , @Path("sub") String sub);

    @GET("/myschool/examTerm/{schoolID}/{className}/{section}")
    Object getExamTerm(@Path("schoolID") String schoolID , @Path("className") String className , @Path("section") String section);

    @GET("/city/getStudentAttendance/{studentId}/{schoolid}/{section}/{className}")
    Object getStudentAttendance(@Path("studentId") String studentId , @Path("schoolid") String schoolid , @Path("section") String section , @Path("className") String className);

}
