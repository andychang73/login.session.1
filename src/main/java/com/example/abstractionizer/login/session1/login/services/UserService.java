package com.example.abstractionizer.login.session1.login.services;

import com.example.abstractionizer.login.session1.db.rmdb.entities.User;
import java.util.Date;
import java.util.Optional;

public interface UserService {

    User create(User user);

    boolean isUserExists(Integer id, String userName);

    Optional<User> getUser(Integer id, String userName);

    void freezeAccount(Integer id);

    void updateLastLoginTime(Integer id, Date lastLoginTime);

    void updateUserInfo(Integer id, User user);

    void changePassword(Integer id, String password);
}
