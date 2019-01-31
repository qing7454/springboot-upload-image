package com.qing.springboot.entity;

import lombok.Data;

import java.util.List;

@Data
public class ResultData {

    private Integer code;
    private String msg;
    private List<Module> data;
}
