package com.cas.service.impl;

import com.cas.Dao.LdapDao;
import com.cas.mapper.AdminMapper;
import com.cas.model.Admin;
import com.cas.model.UserInfo;
import com.cas.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wangliucheng on 2017-10-07 14:37:16
 */
@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminMapper AdminMapper;
    @Autowired
    private LdapDao ldapDao;
    @Override
    public List<Admin> findAllAdmin() {
        return AdminMapper.findAllAdmin();
    }

    @Override
    public Admin getByUid(String uid) {
        return AdminMapper.getByUid(uid);
    }

    @Override
    public void  save(Admin Admin) {
        AdminMapper.save(Admin.getUid(),Admin.getName(),Admin.getMail(),Admin.getPassword());
    }

    @Override
    public void ldap() {
        List<Admin> allAdminNoLdap = AdminMapper.findAllAdminNoLdap();
        if(!CollectionUtils.isEmpty(allAdminNoLdap)){
            allAdminNoLdap.forEach(Admin -> {
                //token.getName()是唯一标识
                UserInfo userInfo = ldapDao.getUserDN(Admin.getUid());
                if(null != userInfo){
                    //添加到ldap
                    String uid = Admin.getUid();
                    String name = Admin.getName();
                    String mail = Admin.getMail();
                    String password = Admin.getPassword();
                    try {
                        ldapDao.add(uid,name,mail,password,"admin");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //修改mysql 数据状态
                AdminMapper.updateStatus(Admin.getUid(),true);

            });

        }

    }
}
