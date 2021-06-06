package com.example.abstractionizer.login.session1.login.controllers;

import com.example.abstractionizer.login.session1.login.business.UserBusiness;
import com.example.abstractionizer.login.session1.models.bo.ChangePasswordBo;
import com.example.abstractionizer.login.session1.models.bo.UserLoginBo;
import com.example.abstractionizer.login.session1.models.bo.UserRegisterBo;
import com.example.abstractionizer.login.session1.models.bo.UserUpdateInfoBo;
import com.example.abstractionizer.login.session1.models.dto.UserInfo;
import com.example.abstractionizer.login.session1.responses.SuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserBusiness userBusiness;

    @PostMapping
    public SuccessResponse register(@RequestBody @Valid UserRegisterBo bo){
        userBusiness.register(bo);
        return new SuccessResponse();
    }

    @GetMapping
    public SuccessResponse validate(@RequestParam("token") String token){
        userBusiness.validate(token);
        return new SuccessResponse();
    }

    @PostMapping("/login")
    public SuccessResponse<UserInfo> login(@RequestBody @Valid UserLoginBo bo, HttpServletRequest request){
        return new SuccessResponse<>(userBusiness.login(bo, request));
    }

    @PutMapping("/changePassword")
    public SuccessResponse changePassword(@RequestBody @Valid ChangePasswordBo bo,
                                          HttpServletRequest request){
        userBusiness.changePassword((UserInfo)request.getSession().getAttribute("user"), bo);
        return new SuccessResponse();
    }

    @PutMapping("/update")
    public SuccessResponse update (@RequestBody @Valid UserUpdateInfoBo bo,
                                   HttpServletRequest request){
        userBusiness.updateUserInfo((UserInfo)request.getSession().getAttribute("user"), bo);
        return new SuccessResponse();
    }

    @GetMapping("/logout")
    public SuccessResponse logout(HttpServletRequest request){
        userBusiness.logout((UserInfo)request.getSession().getAttribute("user"));
        request.getSession().invalidate();
        return new SuccessResponse();
    }
}
