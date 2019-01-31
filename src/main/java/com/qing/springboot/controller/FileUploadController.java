package com.qing.springboot.controller;

import com.qing.springboot.entity.Image;
import com.qing.springboot.service.ImageService;
import com.qing.springboot.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Slf4j
public class FileUploadController {

    private final ImageService imageService;
    private final StorageService storageService;
    @Value("${upload.path}")
    private String imgPath;

    @Autowired
    public FileUploadController(ImageService imageService, StorageService storageService) {
        this.imageService = imageService;
        this.storageService = storageService;
    }

    @PostMapping(value = "upload")
    public String singletonUpload(HttpServletRequest request, HttpServletResponse response) {
        Long l1 = System.currentTimeMillis();
        File uploadFile = new File(imgPath);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        if (!multipartResolver.isMultipart(request)) {
            return null;
        }
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
        MultipartFile file = multipartHttpServletRequest.getFile(fileNames.next());
        String fileName = Objects.requireNonNull(file).getOriginalFilename();
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }
        try {
            file.transferTo(new File(uploadFile, Objects.requireNonNull(fileName)));
            Long l2 = System.currentTimeMillis();
            log.info("time: {}", l2-l1);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "上传失败";
    }

    @PostMapping(value = "batch")
    public String batchUpload(String description, HttpServletRequest request, HttpServletResponse response) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        String realPath = request.getSession().getServletContext().getRealPath("/");

        System.out.println("说说内容="+description);
        log.info("path: {}", realPath);
        if (multipartResolver.isMultipart(request)) {
            File uploadPath = new File(imgPath);
            if (!uploadPath.exists()) uploadPath.mkdirs();
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

            if (null == files || files.isEmpty()) {
                MultiValueMap<String, MultipartFile> fileMultiValueMap = ((MultipartHttpServletRequest) request).getMultiFileMap();
                for (int i = 0; i < fileMultiValueMap.size(); i++) {
                    List<MultipartFile> fileList = fileMultiValueMap.get("file"+i);
                    saveFile(fileList, imgPath);
                }
            } else {
                System.out.println("上传的文件="+ files);
                saveFile(files, imgPath);
            }
        } else {
            return "文件上传失败因为文件为空";
        }
        return "上传成功";
    }

    private void saveFile(List<MultipartFile> files, String uploadPath) {
        for (MultipartFile multipartFile: files) {
            multipartFile.getContentType();
            String fileName = Optional.ofNullable(multipartFile.getOriginalFilename()).orElse("");
            try {
                Image image = new Image();
                String uid = UUID.randomUUID().toString().replaceAll("-", "");
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                String fname = uid+suffix.toLowerCase();
                image.setFilename(fname);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                image.setGmtUpload(formatter.format(new Date()));
                image.setId(uid);
                multipartFile.transferTo(new File(uploadPath, fname));
                storageService.store(multipartFile, image);
                imageService.addImage(image);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    @GetMapping("/download/{id}")
    public String downloadFile(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
        Image image = imageService.getImage(id);
        if (image != null) {
            String fileName = image.getFilename();// 文件名
            //设置文件路径
            Path path = Paths.get(storageService.getGalleryPath(), image.getFilename());
            try {
                byte[] bytes = Files.readAllBytes(path);
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                OutputStream os = response.getOutputStream();
                os.write(bytes);
                response.flushBuffer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "下载失败";
    }
}
