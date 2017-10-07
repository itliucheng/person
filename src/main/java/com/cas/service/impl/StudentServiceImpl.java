package com.cas.service.impl;

import com.cas.Dao.LdapDao;
import com.cas.mapper.StudentMapper;
import com.cas.model.Student;
import com.cas.model.UserInfo;
import com.cas.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wangliucheng on 2017-10-07 01:08:07
 */
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private LdapDao ldapDao;
    @Override
    public List<Student> findAllStudent() {
        return studentMapper.findAllStudent();
    }

    @Override
    public Student getByUid(String uid) {
        return studentMapper.getByUid(uid);
    }

    @Override
    public void  save(Student student) {
        studentMapper.save(student.getUid(),student.getName(),student.getMail(),student.getPassword());
    }

    @Override
    public void ldap() {
        List<Student> allStudentNoLdap = studentMapper.findAllStudentNoLdap();
        if(!CollectionUtils.isEmpty(allStudentNoLdap)){
            allStudentNoLdap.forEach(student -> {
                //token.getName()是唯一标识
                UserInfo userInfo = ldapDao.getUserDN(student.getUid());
                if(null != userInfo){
                    //添加到ldap
                    String uid = student.getUid();
                    String name = student.getName();
                    String mail = student.getMail();
                    String password = student.getPassword();
                    try {
                        ldapDao.add(uid,name,mail,password,"student");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //修改mysql 数据状态
                studentMapper.updateStatus(student.getUid(),true);

            });

        }

    }
}
