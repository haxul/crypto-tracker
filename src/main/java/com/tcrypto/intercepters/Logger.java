package com.tcrypto.intercepters;

import com.tcrypto.dao.LogDao;
import com.tcrypto.models.Log;
import com.tcrypto.models.User;
import com.tcrypto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Logger extends HandlerInterceptorAdapter {

    private final LogDao logDao;
    private final UserService userService;

    public Logger(LogDao logDao, UserService userService) {
        this.logDao = logDao;
        this.userService = userService;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
                                Object handler, Exception ex) throws Exception {
        logDao.save(new Log(request.getRequestURI(), response.getStatus(), userService.getClientIp(request)));
    }
}
