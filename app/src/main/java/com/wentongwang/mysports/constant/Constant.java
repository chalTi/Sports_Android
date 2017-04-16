package com.wentongwang.mysports.constant;

/**
 * Created by Wentong WANG on 2016/9/17.
 */
public class Constant {

    //*********************** local host *******************************/
    //this is for test, in fact, should save in SP
//    public static final String HOST = "http://35.156.49.76:8080/sports";
    public static final String HOST = "http://35.156.49.76:8080/sports/";

    //*********************** servlet path *******************************//
    public static final String LOGIN_PATH = "/user/login";
    public static final String SIGN_UP_PATH = "/user/register";
    public static final String GET_NEWS_PATH = "/articles/getarticles";
    public static final String GET_AGENDA_PATH = "/agenda/getagenda";
    public static final String GET_EVENT_PATH = "/home/gethomesportevent";
    public static final String CREATE_EVENT_PATH = "/sport/events/create";

    //*********************** SharedPreference *******************************//
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_PASSWORD = "USER_PASSWORD";


}
