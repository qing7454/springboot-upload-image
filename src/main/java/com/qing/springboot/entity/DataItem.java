package com.qing.springboot.entity;

import lombok.Data;

import java.util.List;

@Data
public class DataItem {

    private String publishTime;
    private String coverUri;
    private Long created;
    private String description;
    private String duration;
    private Integer id;
    private Integer allTalkCount;
    private Integer playCount;
    private Integer grade;
    private Integer goodCount;
    private Integer type;
    private Integer isAlreadyGrade;
    private List<String> screenShortUrls;
    private String title;
    private String videoUri;
    private Boolean enableSpeed;
    private Boolean isFree;
    private Boolean alreadyClickGood;
    private Double price;
}
