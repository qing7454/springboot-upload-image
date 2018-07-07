package com.qing.springboot.service;

import com.qing.springboot.entity.Image;

public interface ImageService {

    /**
     * 保存图像数据到数据库
     * @param image 图像
     */
    void addImage(Image image);

    /**
     * 根据id获取存储图片对象
     * @param id id
     * @return image
     */
    Image getImage(String id);
}
