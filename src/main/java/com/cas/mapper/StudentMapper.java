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
