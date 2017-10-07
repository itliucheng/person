package com.cas.model;

import lombok.Data;

/**
 * Created by wangliucheng on 2017-107 00:56:32
 */
@Data
public class Student {
    private Integer id;
    private String uid;
    private String name;
    private Integer role;
    private String mail;
    private String password;
    private Boolean status;
}
