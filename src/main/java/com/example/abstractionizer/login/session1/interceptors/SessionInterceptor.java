package com.example.abstractionizer.login.session1.interceptors;

import com.example.abstractionizer.login.session1.enums.ErrorCode;
import com.example.abstractionizer.login.session1.exceptions.CustomException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(Objects.isNull(session)){
            throw new CustomException(ErrorCode.USER_LOGGED_OUT);
        }
        return true;
    }
}
