package com.qing.springboot.controller;

import com.qing.springboot.entity.Image;
import com.qing.springboot.service.ImageService;
import com.qing.springboot.service.ProductService;
import com.qing.springboot.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final StorageService storageService;
    private final ImageService imageService;
    private final ProductService productService;

    @Autowired
    public ImageController(StorageService storageService, ImageService imageService, ProductService productService) {
        this.storageService = storageService;
        this.imageService = imageService;
        this.productService = productService;
    }

    @GetMapping(value = "")
    public ModelAndView uploadPage() {
        return new ModelAndView("/upload");
    }

    @PostMapping("/upload")
    public Image addImage(@RequestParam("image") MultipartFile file) {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        int index = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
        String suffix = file.getOriginalFilename().substring(index);
        String filename = id + suffix;

        Image image = new Image();
        image.setId(id);
        image.setFilename(filename);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String gmtUpload = now.format(formatter);
        image.setGmtUpload(gmtUpload);
        storageService.store(file, image);
        imageService.addImage(image);
        return image;
    }

    @GetMapping(value = "/{id}")
    public void getImage(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
        Image image = imageService.getImage(id);
        if (Objects.isNull(image)) {
            try {
                response.getWriter().write("无此id");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        response.setContentType("image/jpeg");
        String filename = image.getFilename();
        Path path = Paths.get(storageService.getGalleryPath(), filename);
        try {
            byte[] bytes = Files.readAllBytes(path);
            response.getOutputStream().write(bytes);
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据产品类型下载照片包
     */
    @RequestMapping("/downloadBatch")
    public void downloadBatch(HttpServletRequest request, HttpServletResponse response, String certId, String certName){
        //获取要下载的图片
        List<String> fileNames = productService.getCertImageById(certId);

        //根据包名获取待下载的文件    文件名-字节数组的形式
        Map<String, byte[]> files = new HashMap<>();
        for(String fileName : fileNames){
            byte[] f = productService.getByteSource(storageService.getGalleryPath(), fileName);
            if(f!=null){
                files.put(fileName, f);
            }
        }

        //设置下载的压缩包名
        String zipName = certName + "_"+ certId + ".zip";

        //根据文件，进行压缩，批量下载
        if(files.size() > 0){
            productService.downloadBatchByFile(response, files, zipName);
        }

    }
}