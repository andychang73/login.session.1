package com.example.abstractionizer.login.session1.login.services.impl;

import com.example.abstractionizer.login.session1.constants.RedisConstant;
import com.example.abstractionizer.login.session1.login.services.UserLoginService;
import com.example.abstractionizer.login.session1.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private RedisUtil redisUtil;

    private final long duration = 15L;

    @Override
    public boolean authenticate(String enteredPassword, String password) {
        return Objects.equals(enteredPassword, password);
    }

    @Override
    public long countLoginFailure(String userName) {
        long count = 1L;
        String key = RedisConstant.getUserLoginFailureCount(userName);
        if(redisUtil.isKeyExists(key)){
            return redisUtil.increment(key, count);
        }
        redisUtil.set(key, count, duration, TimeUnit.MINUTES);
        return count;
    }

    @Override
    public void setUserLoggedIn(Integer id) {
        redisUtil.set(RedisConstant.getUserCurrentlyLoggedIn(id), id, duration, TimeUnit.MINUTES);
    }

    @Override
    public boolean isUserCurrentlyLoggedIn(Integer id) {
        return redisUtil.isKeyExists(RedisConstant.getUserCurrentlyLoggedIn(id));
    }
}
