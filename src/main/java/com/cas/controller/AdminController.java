package com.cas.controller;

import com.cas.config.AuthorityInfo;
import com.cas.model.Admin;
import com.cas.model.UserInfo;
import com.cas.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangliucheng on 2017-10-07 14:30:48
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @RequestMapping("/page")
    public String index(Model model, HttpServletRequest request) {
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
        return "admin";
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public Object findAllAdmin() {
        Map<String,Object> map = new HashMap<>();
        List<Admin> allAdmin = adminService.findAllAdmin();
        map.put("data",allAdmin);
        return map;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(Admin Admin) {
        Map<String,Object> map = new HashMap<>();
        map.put("success",false);
        Admin s = adminService.getByUid(Admin.getUid());
        if(null==s){
            adminService.save(Admin);
            map.put("success",true);
        }else{
            map.put("result","用户已经存在，不能添加");
        }
        return map;
    }

    @RequestMapping(value = "/ldap",method = RequestMethod.GET)
    @ResponseBody
    public Object ldap() {
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        adminService.ldap();
        return map;
    }
}
