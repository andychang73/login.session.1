package com.example.abstractionizer.login.session1.login.services.impl;

import com.example.abstractionizer.login.session1.db.rmdb.entities.User;
import com.example.abstractionizer.login.session1.db.rmdb.mappers.UserMapper;
import com.example.abstractionizer.login.session1.enums.ErrorCode;
import com.example.abstractionizer.login.session1.exceptions.CustomException;
import com.example.abstractionizer.login.session1.login.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User create(User user) {
        if(userMapper.insert(user) != 1){
            throw new CustomException(ErrorCode.DATA_INSERT_FAILED);
        }
        return user;
    }

    @Override
    public boolean isUserExists(Integer id, String userName) {
        return Objects.nonNull(userMapper.selectByIdOrUserName(id, userName));
    }

    @Override
    public Optional<User> getUser(Integer id, String userName) {
        return Optional.ofNullable(userMapper.selectByIdOrUserName(id, userName));
    }

    @Override
    public void freezeAccount(Integer id) {
        if(userMapper.updateStatus(id, false) != 1){
            throw new CustomException(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public void updateLastLoginTime(Integer id, Date lastLoginTime) {
        if(userMapper.updateUser(id, null, null, null, null, lastLoginTime) != 1){
            throw new CustomException(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public void updateUserInfo(Integer id, User user) {
        if(userMapper.updateUser(id, user.getUserName(), null, user.getEmail(), user.getPhone(), null) != 1){
            throw new CustomException(ErrorCode.DATA_UPDATE_FAILED);
        }
    }

    @Override
    public void changePassword(Integer id, String password) {
        if(userMapper.updateUser(id, null, password, null, null, null) != 1){
            throw new CustomException(ErrorCode.DATA_UPDATE_FAILED);
        }
    }
}
