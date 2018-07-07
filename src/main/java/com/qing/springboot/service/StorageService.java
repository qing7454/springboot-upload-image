package com.qing.springboot.service;

import com.qing.springboot.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    /**
     * 保存图片
     * @param file 图片文件
     * @param image 图片对象
     */
    void store(MultipartFile file, Image image);

    /**
     * 获取不同系统下的路径
     * @return 路径
     */
    String getGalleryPath();
}
