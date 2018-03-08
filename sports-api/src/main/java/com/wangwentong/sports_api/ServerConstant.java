package com.wangwentong.sports_api;

/**
 * Created by Wentong WANG on 2016/9/15.
 */
public class ServerConstant {

    //*********************** send value 发送给服务器的数据标识*******************************/
    // 其他参数信息
    public static final String PHONE_TYPE = "01";// 01 代表android
    // 管理服务器信息
    public static final String APPTYPE = "01";// 01-->
    public static final String REALVERSION = "8";
    public static final String VERSION = "132";


    //*********************** return code from server*******************************/
    // code
    public static final String CODE = "code";
    public static final String SUCCESS = "01";
    public static final String FAILED = "02";
    public static final String ERROR = "03";
    // result
    public static final String RESULT = "result";

    // message
    public static final String MESSAGE = "message";

    // size
    public static final String JSONARRAY_SIZE = "size";



    //*********************** local host *******************************/
    //this is for test, in fact, should save in SP
//    public static final String HOST = "http://35.156.49.76:8080/sports";
    public static final String HOST = "http://35.156.49.76:8080/sports/";
    public static final String LOCAL_HOST = "http://192.168.1.17:8080/sports/";

    //*********************** servlet path *******************************//
    public static final String LOGIN_PATH = "/user/login";
    public static final String SIGN_UP_PATH = "/user/register";
    public static final String GET_NEWS_PATH = "/articles/getarticles";
    public static final String GET_AGENDA_PATH = "/agenda/getagenda";
    public static final String GET_EVENT_PATH = "/home/sportevents?user_like=";
    public static final String CREATE_EVENT_PATH = "/sport/events/create";

}
