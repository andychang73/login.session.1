package com.example.abstractionizer.login.session1.login.services;

public interface UserLoginService {

    boolean authenticate(String enteredPassword, String password);

    long countLoginFailure(String userName);

    void setUserLoggedIn(Integer id);

    void deleteUserLoggedIn(Integer id);

    boolean isUserCurrentlyLoggedIn(Integer id);
}
