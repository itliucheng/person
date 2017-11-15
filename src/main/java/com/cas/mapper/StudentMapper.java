package com.cas.mapper;

import com.cas.model.Student;
import com.cas.model.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangliucheng on 2017/10/7 0007.
 */
@Mapper
@Service
public interface StudentMapper {
    //Student findAllStudent(@Param("name")String name);
    @Select("select * from student")
    List<Student> findAllStudent();
    @Select("select * from student where uid = #{uid}")
    Student getByUid(@Param("uid") String uid);

    @Insert("insert into student(uid,name,role,mail,password,status) values(#{uid},#{name},1,#{mail},#{password},0)")
    void save(@Param("uid") String uid,@Param("name") String name,@Param("mail") String mail,@Param("password") String password);


    @Select("select * from student where status = 0")
    List<Student> findAllStudentNoLdap();
    @Update("UPDATE student SET STATUS = #{status} WHERE uid= #{uid}")
    void updateStatus(@Param("uid") String uid, @Param("status") boolean status);
}
//sql
/*
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) NOT NULL DEFAULT '' COMMENT '登陆名',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '显示名',
  `role` int(11) NOT NULL DEFAULT '0' COMMENT '角色 1学生 2管理员',
  `mail` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否导入ldap 0未导入 1导入',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) NOT NULL DEFAULT '' COMMENT '登陆名',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '显示名',
  `role` int(11) NOT NULL DEFAULT '0' COMMENT '角色 1学生 2管理员',
  `mail` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否导入ldap 0未导入 1导入',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8
* */