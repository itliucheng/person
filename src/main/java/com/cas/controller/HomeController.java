package com.cas.controller;

import com.cas.config.AuthorityInfo;
import com.cas.mapper.StudentMapper;
import com.cas.model.Student;
import com.cas.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.ObjectStreamClass;
import java.security.Principal;
import java.util.List;
import java.util.Set;


/**
 * Created by wangliucheng on 2017/9/19 0019.
 */
@Controller
public class HomeController {

    @Autowired
    private StudentMapper studentMapper;

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping("/")
    public String index(Model model,HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        String name = userInfo.getUid();
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
    @RequestMapping("/noAuthority")
    public String error(Model model,HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        String name = userInfo.getUid();
        String role = null;
        Set<AuthorityInfo> authorities = userInfo.getAuthorities();
        if(authorities.iterator().hasNext()){
            AuthorityInfo next = authorities.iterator().next();
            role = next.getAuthority();
        }
        request.setAttribute("role",role);
        request.setAttribute("name",name);
        return "noAuthority";
    }
}
