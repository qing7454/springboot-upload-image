package com.qing.springboot.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@ToString
@Entity
@Table(name = "image")
public class Image {

    @Id
    private String id;

    private String filename;

    private String gmtUpload;
}
