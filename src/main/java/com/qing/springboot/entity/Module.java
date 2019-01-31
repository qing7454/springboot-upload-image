package com.qing.springboot.entity;

import lombok.Data;

import java.util.List;

@Data
public class Module {

    private Integer id;
    private Integer pid;
    private String title;
    private String description;
    private List<Object> childs;
    private String icon;
    private String dataType;
    private String style;
    private String dataSource;
    private List<Object> data;
    private String sql;
}
