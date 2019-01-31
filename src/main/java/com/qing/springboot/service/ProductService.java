package com.qing.springboot.service;

import com.qing.springboot.entity.Image;
import com.qing.springboot.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ProductService {

    private final ImageRepository imageRepository;

    @Autowired
    public ProductService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     * 根据包名获取文件字节数组
     */
    public byte[] getByteSource(String sourcePath, String fileName){
        byte[] bag  = null;
        try{
            Path path = Paths.get(sourcePath, fileName);
            try {
                bag = Files.readAllBytes(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return bag;
    }


    /**
     * 根据产品id 获取待下载的包名
     * @param certId 证件id
     * @return 图片名称
     */
    public List<String> getCertImageById(String certId) {
        //List<Image> packageNames = imageRepository.findAllById(certId);
        List<Image> packageNames = imageRepository.findAllByTodayOrLaterUpload("2019-01-31");
        return packageNames.stream().map(Image::getFilename).collect(Collectors.toList());
    }

    /**
     * 根据文件，进行压缩，批量下载
     * @param response response
     * @param files 文件
     */
    public void downloadBatchByFile(HttpServletResponse response, Map<String, byte[]> files, String zipName){
        try{
            response.setContentType("application/x-msdownload");
            response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(zipName, "utf-8"));

            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            BufferedOutputStream bos = new BufferedOutputStream(zos);

            for(Map.Entry<String, byte[]> entry : files.entrySet()){
                String fileName = entry.getKey();            //每个zip文件名
                byte[]    file = entry.getValue();            //这个zip文件的字节

                BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(file));
                zos.putNextEntry(new ZipEntry(fileName));

                int len = 0;
                byte[] buf = new byte[10 * 1024];
                while( (len=bis.read(buf, 0, buf.length)) != -1){
                    bos.write(buf, 0, len);
                }
                bis.close();
                bos.flush();
            }
            bos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
