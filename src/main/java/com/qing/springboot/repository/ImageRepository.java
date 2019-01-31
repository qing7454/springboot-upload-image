package com.qing.springboot.repository;

import com.qing.springboot.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, String> {

    List<Image> findAllById(String id);

    @Query(value = "select a from Image as a where date_format(a.gmtUpload,'%Y-%m-%d') <= date_format(:date,'%Y-%m-%d')")
    List<Image> findAllByTodayOrLaterUpload(@Param("date") String date);
}
