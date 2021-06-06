package com.example.abstractionizer.login.session1.login.business;

import com.example.abstractionizer.login.session1.models.bo.ChangePasswordBo;
import com.example.abstractionizer.login.session1.models.bo.UserLoginBo;
import com.example.abstractionizer.login.session1.models.bo.UserRegisterBo;
import com.example.abstractionizer.login.session1.models.bo.UserUpdateInfoBo;
import com.example.abstractionizer.login.session1.models.dto.UserInfo;

import javax.servlet.http.HttpServletRequest;

public interface UserBusiness {

    void register(UserRegisterBo bo);

    void validate(String token);

    UserInfo login(UserLoginBo bo, HttpServletRequest request);

    void updateUserInfo(UserInfo userInfo, UserUpdateInfoBo userUpdateInfoBo);

    void changePassword(UserInfo userInfo, ChangePasswordBo bo);

    void logout(UserInfo userInfo);
}
