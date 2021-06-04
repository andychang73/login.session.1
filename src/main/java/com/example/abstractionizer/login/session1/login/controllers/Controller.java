package com.example.abstractionizer.login.session1.login.controllers;

import com.example.abstractionizer.login.session1.db.rmdb.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class Controller {

    @GetMapping
    public String getSessionId(@RequestParam("username") String username, HttpServletRequest request){
        HttpSession session = request.getSession();

        User user = new User()
                .setUserName(username)
                .setPassword("123456")
                .setEmail("abcdefg")
                .setPhone("12345678");
        session.setAttribute(session.getId(), user);
        log.info("session id: {}", session.getId());
        return session.getId();
    }
}
