package com.example.abstractionizer.login.session1.login.business;

import com.example.abstractionizer.login.session1.models.bo.UserRegisterBo;

import javax.servlet.http.HttpServletRequest;

public interface UserBusiness {

    void register(UserRegisterBo bo);

    void test(HttpServletRequest request);
}
