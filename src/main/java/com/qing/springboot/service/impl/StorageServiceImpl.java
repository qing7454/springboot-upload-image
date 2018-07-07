package com.qing.springboot.service.impl;

import com.qing.springboot.entity.Image;
import com.qing.springboot.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Transactional
@Slf4j
public class StorageServiceImpl implements StorageService {

    private Path path = null;

    @Value("${win.gallery.dir}")
    private String winGallery;

    @Value("${linux.gallery.dir}")
    private String linuxGallery;

    @Override
    public void store(MultipartFile file, Image image) {
        Long t1 = System.currentTimeMillis();
        if (file.isEmpty()) {
            throw new RuntimeException("Fail to store empty file");
        }
        try {
            path = Paths.get(getGalleryPath(), image.getFilename());
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            Long t2 = System.currentTimeMillis();
            log.info("上传耗时：{} 毫秒", t2 - t1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getGalleryPath() {
        String osName = System.getProperty("os.name");
        String galleryPath = null;
        if (osName.startsWith("Windows")) {
            // 在 Windows 操作系统上
            galleryPath = winGallery;
        } else if (osName.startsWith("Linux")) {
            // 在 Linux 操作系统上
            galleryPath = linuxGallery;
        }
        return galleryPath;
    }
}
