package com.cas.service;

import com.cas.model.Student;

import java.util.List;

/**
 * Created by wangliucheng on 2017/10/7 0007.
 */
public interface StudentService {

    List<Student> findAllStudent();

    Student getByUid(String uid);

    void save(Student student);

    void ldap();
}
