package com.cas.model;

import lombok.Data;

/**
 * Created by wangliucheng on 2017-10-07 14:35:33
 */
@Data
public class Admin {
    private Integer id;
    private String uid;
    private String name;
    private Integer role;
    private String mail;
    private String password;
    private Boolean status;
}
