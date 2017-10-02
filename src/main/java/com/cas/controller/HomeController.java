package com.cas.controller;

import com.cas.model.RoleEnum;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static com.cas.model.RoleEnum.STUDENT;

/**
 * Created by wangliucheng on 2017/9/19 0019.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model,HttpServletRequest request) {

        Principal userPrincipal = request.getUserPrincipal();
        String name = userPrincipal.getName();
        return "index";
    }


    @RequestMapping("/hello")
    public String hello() {
        return "不验证哦";
    }

    @PreAuthorize("hasAuthority('student')")//有TEST权限的才能访问
    @RequestMapping("/security")
    public String security() {
        return "hello world security";
    }

    @PreAuthorize("hasAuthority('ADMIN')")//必须要有ADMIN权限的才能访问
    @RequestMapping("/authorize")
    public String authorize() {
        return "有权限访问";
    }
}
