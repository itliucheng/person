package com.cas.controller;

import com.cas.config.AuthorityInfo;
import com.cas.model.UserInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Set;


/**
 * Created by wangliucheng on 2017/9/19 0019.
 */
@Controller
public class HomeController {

    //@PreAuthorize("hasAuthority('student')")//有TEST权限的才能访问
    @RequestMapping("/")
    public String index(Model model,HttpServletRequest request) {

        Principal userPrincipal = request.getUserPrincipal();
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        String name = userInfo.getName();
        String role = null;
        Set<AuthorityInfo> authorities = userInfo.getAuthorities();
        if(authorities.iterator().hasNext()){
            AuthorityInfo next = authorities.iterator().next();
            role = next.getAuthority();
        }
        request.setAttribute("role",role);
        request.setAttribute("name",name);
        return "index";
    }
}
