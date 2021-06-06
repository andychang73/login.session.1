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
public class Controller {

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
//    @GetMapping
//    public void getSessionId(@RequestParam("username") String username, HttpServletRequest request){
//        HttpSession session = request.getSession();
//
//        if(Objects.isNull(session)){
//            log.info("================");
//            return;
//        }
//        String sessionId = session.getId();
//        User user = new User()
//                .setUserName(username)
//                .setPassword("123456")
//                .setEmail("aaaa")
//                .setPhone("1111111");
//        log.info("session id: {}", session.getId());
//        log.info("user: {}", user);
//        session.setAttribute("user", user);
//
//        if(session.isNew()){
//            log.info("new");
//        }else{
//            log.info("not new");
//        }
//    }
}
