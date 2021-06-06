package com.example.abstractionizer.login.session1.login.controllers;

import com.example.abstractionizer.login.session1.db.rmdb.entities.User;
import com.example.abstractionizer.login.session1.login.business.UserBusiness;
import com.example.abstractionizer.login.session1.models.bo.UserRegisterBo;
import com.example.abstractionizer.login.session1.responses.SuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;


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
