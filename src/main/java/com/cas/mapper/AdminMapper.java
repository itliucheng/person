package com.cas.mapper;

import com.cas.model.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangliucheng on 2017/10/7 0007.
 */
@Mapper
@Service
public interface AdminMapper {
    @Select("select * from admin")
    List<Admin> findAllAdmin();
    @Select("select * from admin where uid = #{uid}")
    Admin getByUid(@Param("uid") String uid);

    @Insert("insert into admin(uid,name,role,mail,password,status) values(#{uid},#{name},2,#{mail},#{password},0)")
    void save(@Param("uid") String uid, @Param("name") String name, @Param("mail") String mail, @Param("password") String password);


    @Select("select * from admin where status = 0")
    List<Admin> findAllAdminNoLdap();
    @Update("UPDATE admin SET STATUS = #{status} WHERE uid= #{uid}")
    void updateStatus(@Param("uid") String uid, @Param("status") boolean status);
}
