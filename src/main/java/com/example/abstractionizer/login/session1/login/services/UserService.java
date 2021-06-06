package com.example.abstractionizer.login.session1.login.services;

import com.example.abstractionizer.login.session1.db.rmdb.entities.User;

public interface UserService {

    User create(User user);

    boolean isUserExists(Integer id, String userName);
}
