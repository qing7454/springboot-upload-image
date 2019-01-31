package com.qing.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.qing.springboot.bean.ResultData;
import com.qing.springboot.utils.HttpClientUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JsonController {

    @GetMapping(value = "/json")
    public Object getJson() {
        return "";
    }
}
