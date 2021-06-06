package com.example.abstractionizer.login.session1.login.services;

import com.example.abstractionizer.login.session1.db.rmdb.entities.User;

import java.util.Optional;

public interface UserRegistrationService {

    boolean isUserNameCurrentlyRegistering(String userName);

    void setCurrentlyRegisteringUserName(String userName);

    void deleteCurrentlyRegisteringUserName(String userName);

    void sendValidationMail(String to, String token);

    void setUserRegistrationInfo(String token, User user);

    Optional<User> getUserRegistrationInfo(String token);

    void deleteUserRegistrationInfo(String token);
}
