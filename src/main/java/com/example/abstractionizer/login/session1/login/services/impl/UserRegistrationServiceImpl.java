package com.example.abstractionizer.login.session1.login.services.impl;

import com.example.abstractionizer.login.session1.constants.RedisConstant;
import com.example.abstractionizer.login.session1.db.rmdb.entities.User;
import com.example.abstractionizer.login.session1.login.services.UserRegistrationService;
import com.example.abstractionizer.login.session1.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JavaMailSender javaMailSender;

    private final long duration = 5L;

    @Override
    public boolean isUserNameCurrentlyRegistering(String userName) {
        return redisUtil.isKeyExists(RedisConstant.getCurrentlyRegisteringUserName(userName));
    }

    @Override
    public void setCurrentlyRegisteringUserName(String userName) {
        redisUtil.set(RedisConstant.getCurrentlyRegisteringUserName(userName), userName, duration, TimeUnit.MINUTES);
    }

    @Override
    public void sendValidationMail(String to, String token) {
        javaMailSender.send(getSimpleMailMessage(to, token));
    }

    @Override
    public void setUserRegistrationInfo(String token, User user) {
        redisUtil.set(RedisConstant.getUserRegisterInfo(token), user, duration, TimeUnit.MINUTES);
    }

    private SimpleMailMessage getSimpleMailMessage(String to, String token){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("noreply@abtractionizer.com");
        mailMessage.setTo(to);
        mailMessage.setSubject("Email Validation");
        mailMessage.setText(getValidationUrl(token));
        return mailMessage;
    }

    private String getValidationUrl(String token){
        return "http://127.0.0.1:8080/api/user?token=" + token;
    }


}
