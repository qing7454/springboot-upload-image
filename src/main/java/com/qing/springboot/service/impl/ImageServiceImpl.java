package com.qing.springboot.service.impl;

import com.qing.springboot.entity.Image;
import com.qing.springboot.repository.ImageRepository;
import com.qing.springboot.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void addImage(Image image) {
        imageRepository.save(image);
    }

    @Override
    public Image getImage(String id) {
        return imageRepository.getOne(id);
    }
}
