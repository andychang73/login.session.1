package com.example.abstractionizer.login.session1.login.services.impl;

import com.example.abstractionizer.login.session1.db.rmdb.entities.User;
import com.example.abstractionizer.login.session1.db.rmdb.mappers.UserMapper;
import com.example.abstractionizer.login.session1.enums.ErrorCode;
import com.example.abstractionizer.login.session1.exceptions.CustomException;
import com.example.abstractionizer.login.session1.login.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
}
