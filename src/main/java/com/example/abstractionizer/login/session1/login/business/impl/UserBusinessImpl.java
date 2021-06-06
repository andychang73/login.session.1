package com.example.abstractionizer.login.session1.login.business.impl;

import com.example.abstractionizer.login.session1.db.rmdb.entities.User;
import com.example.abstractionizer.login.session1.enums.ErrorCode;
import com.example.abstractionizer.login.session1.exceptions.CustomException;
import com.example.abstractionizer.login.session1.login.business.UserBusiness;
import com.example.abstractionizer.login.session1.login.services.UserRegistrationService;
import com.example.abstractionizer.login.session1.login.services.UserService;
import com.example.abstractionizer.login.session1.models.bo.UserRegisterBo;
import com.example.abstractionizer.login.session1.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.UUID;

@Slf4j
@Service
public class UserBusinessImpl implements UserBusiness {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Override
    public synchronized void register(UserRegisterBo bo) {
        if(userService.isUserExists(null, bo.getUserName()) || userRegistrationService.isUserNameCurrentlyRegistering(bo.getUserName())){
            throw new CustomException(ErrorCode.USERNAME_EXISTS);
        }
        userRegistrationService.setCurrentlyRegisteringUserName(bo.getUserName());

        User user = new User()
                .setUserName(bo.getUserName())
                .setPassword(MD5Util.md5(bo.getPassword()))
                .setEmail(bo.getEmail())
                .setPhone(bo.getPhone())
                .setStatus(true);

        String token = UUID.randomUUID().toString();
        userRegistrationService.sendValidationMail(bo.getEmail(), token);
        userRegistrationService.setUserRegistrationInfo(token, user);
    }

    @Override
    public void test(HttpServletRequest request) {
        log.info("!!!");
            HttpSession session = request.getSession();
            for(String key : Collections.list(session.getAttributeNames())){
                log.info("key : {}  value: {}", key, session.getAttribute(key));
            }
        log.info("@@@");
    }
}
