package com.example.abstractionizer.login.session1.login.services;

import com.example.abstractionizer.login.session1.db.rmdb.entities.User;

public interface UserRegistrationService {

    boolean isUserNameCurrentlyRegistering(String userName);

    void setCurrentlyRegisteringUserName(String userName);

    void sendValidationMail(String to, String token);

    void setUserRegistrationInfo(String token, User user);
}
