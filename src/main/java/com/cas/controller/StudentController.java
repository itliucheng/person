package com.cas.controller;

import com.cas.model.Student;
import com.cas.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangliucheng on 2017-10-07 01:08:54
 */
@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public Object findAllStudent() {
        Map<String,Object> map = new HashMap<>();
        List<Student> allStudent = studentService.findAllStudent();
        map.put("data",allStudent);
        return map;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(Student student) {
        Map<String,Object> map = new HashMap<>();
        map.put("success",false);
        Student s = studentService.getByUid(student.getUid());
        if(null==s){
            studentService.save(student);
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
        studentService.ldap();
        return map;
    }
}
