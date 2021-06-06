package com.example.abstractionizer.login.session1.login.business;

import com.example.abstractionizer.login.session1.models.bo.UserRegisterBo;

public interface UserBusiness {

    void register(UserRegisterBo bo);

    void validate(String token);
}
