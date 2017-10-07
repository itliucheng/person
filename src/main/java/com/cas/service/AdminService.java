package com.cas.service;

import com.cas.model.Admin;

import java.util.List;

/**
 * Created by wangliucheng on 2017/10/7 0007.
 */
public interface AdminService {

    List<Admin> findAllAdmin();

    Admin getByUid(String uid);

    void save(Admin Admin);

    void ldap();
}
