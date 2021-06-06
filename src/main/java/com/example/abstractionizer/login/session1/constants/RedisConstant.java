package com.example.abstractionizer.login.session1.constants;

public class RedisConstant {

    public static final String USER_NAME_REGISTERING = "LOGIN:USER:REGISTERING:%s";
    public static final String USER_REGISTER_INFO = "LOGIN:USER:REGISTER:INFO:%s";
    public static final String USER_LOGIN_FAILURE_COUNT = "LOGIN:USER:FAILURE:COUNT:%s";
    public static final String USER_CURRENTLY_LOGGED_IN = "LOGIN:USER:LOGGED_IN:%s";

    public static String getCurrentlyRegisteringUserName(String userName){return String.format(USER_NAME_REGISTERING, userName);}

    public static String getUserRegisterInfo(String token){ return String.format(USER_REGISTER_INFO, token);}

    public static String getUserLoginFailureCount(String userName){ return String.format(USER_LOGIN_FAILURE_COUNT, userName); }

    public static String getUserCurrentlyLoggedIn(Integer id){return String.format(USER_CURRENTLY_LOGGED_IN, id);}
}
