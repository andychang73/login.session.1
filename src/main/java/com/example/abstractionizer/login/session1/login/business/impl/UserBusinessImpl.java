package com.example.abstractionizer.login.session1.login.business.impl;

import com.example.abstractionizer.login.session1.db.rmdb.entities.User;
import com.example.abstractionizer.login.session1.enums.ErrorCode;
import com.example.abstractionizer.login.session1.exceptions.CustomException;
import com.example.abstractionizer.login.session1.login.business.UserBusiness;
import com.example.abstractionizer.login.session1.login.services.UserLoginService;
import com.example.abstractionizer.login.session1.login.services.UserRegistrationService;
import com.example.abstractionizer.login.session1.login.services.UserService;
import com.example.abstractionizer.login.session1.models.bo.ChangePasswordBo;
import com.example.abstractionizer.login.session1.models.bo.UserLoginBo;
import com.example.abstractionizer.login.session1.models.bo.UserRegisterBo;
import com.example.abstractionizer.login.session1.models.bo.UserUpdateInfoBo;
import com.example.abstractionizer.login.session1.models.dto.UserInfo;
import com.example.abstractionizer.login.session1.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class UserBusinessImpl implements UserBusiness {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserLoginService userLoginService;

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
                .setPhone(bo.getPhone() == null ? null : bo.getPhone())
                .setStatus(true);

        String token = UUID.randomUUID().toString();
        userRegistrationService.setUserRegistrationInfo(token, user);
        userRegistrationService.sendValidationMail(bo.getEmail(), token);
    }

    @Override
    public void validate(String token) {
        if(Objects.isNull(token) || token.isEmpty()){
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        User user = userRegistrationService.getUserRegistrationInfo(token).orElseThrow(()-> new CustomException(ErrorCode.ACCOUNT_VALIDATION_EXPIRED));

        userService.create(user);
        userRegistrationService.deleteCurrentlyRegisteringUserName(user.getUserName());
        userRegistrationService.deleteUserRegistrationInfo(token);
    }

    @Override
    public UserInfo login(UserLoginBo bo, HttpServletRequest request) {
        User user = userService.getUser(null, bo.getUserName()).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if(!userLoginService.authenticate(MD5Util.md5(bo.getPassword()), user.getPassword())){
            if(userLoginService.countLoginFailure(user.getUserName()) >= 3){
                userService.freezeAccount(user.getId());
                throw new CustomException(ErrorCode.ACCOUNT_FROZEN);
            }
            throw new CustomException(ErrorCode.INVALID_CREDENTIAL);
        }

        if(userLoginService.isUserCurrentlyLoggedIn(user.getId())){
            throw new CustomException(ErrorCode.USER_LOGGED_IN);
        }

        userLoginService.setUserLoggedIn(user.getId());
        userService.updateLastLoginTime(user.getId(), new Date());
        UserInfo userInfo = getUserInfo(user);

        HttpSession session = request.getSession();
        session.setAttribute("user", userInfo);
        return userInfo;
    }

    @Override
    public void updateUserInfo(UserInfo userInfo, UserUpdateInfoBo userUpdateInfoBo) {
        if(Objects.isNull(userUpdateInfoBo.getUserName()) && Objects.isNull(userUpdateInfoBo.getEmail()) && Objects.isNull(userUpdateInfoBo.getPhone())){
            throw new CustomException(ErrorCode.NO_DATA_TO_UPDATE);
        }

        if(!userService.isUserExists(userInfo.getId(), null)){
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        User user = new User()
                .setUserName(userUpdateInfoBo.getUserName())
                .setEmail(userUpdateInfoBo.getEmail())
                .setPhone(userUpdateInfoBo.getPhone());

        userService.updateUserInfo(userInfo.getId(), user);
    }

    @Override
    public void changePassword(UserInfo userInfo, ChangePasswordBo bo) {
        if(Objects.equals(bo.getOldPassword(), bo.getNewPassword())){
            throw new CustomException(ErrorCode.NEW_OLD_PASSWORD_SAME);
        }
        if(!Objects.equals(bo.getNewPassword(), bo.getConfirmPassword())){
            throw new CustomException(ErrorCode.NEW_PASSWORD_INCONSISTENCY);
        }

        User user = userService.getUser(userInfo.getId(), null).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        if(!userLoginService.authenticate(MD5Util.md5(bo.getOldPassword()), user.getPassword())){
            throw new CustomException(ErrorCode.INVALID_CREDENTIAL);
        }

        userService.changePassword(user.getId(), MD5Util.md5(bo.getConfirmPassword()));
    }

    @Override
    public void logout(UserInfo userInfo) {
        userLoginService.deleteUserLoggedIn(userInfo.getId());
    }

    private UserInfo getUserInfo(User user){
        return new UserInfo()
                .setId(user.getId())
                .setUserName(user.getUserName())
                .setEmail(user.getEmail())
                .setPhone(user.getPhone());
    }

}
