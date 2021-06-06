package com.example.abstractionizer.login.session1.constants;

public class RedisConstant {

    public static final String USER_NAME_REGISTERING = "login:user:registering:%s";
    public static final String USER_REGISTER_INFO = "login:user:register:info:%s";

    public static String getCurrentlyRegisteringUserName(String userName){
        return String.format(USER_NAME_REGISTERING, userName);
    }

    public static String getUserRegisterInfo(String token){
        return String.format(USER_REGISTER_INFO, token);
    }
}
