package com.qing.springboot.entity;

import lombok.Data;

@Data
public class Profile {

    private Integer id;
    private Integer gold;
    private Boolean isAdmin;
    private Boolean isVip;
    private Long createTime;
    private Integer longCount;
    private String master;
    private String deviceId;
    private String thumb;
    private Long expire;
    private String name;
}
