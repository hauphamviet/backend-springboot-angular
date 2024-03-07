package com.example.ogani.repository;

import com.example.ogani.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM images WHERE uploaded_by = ?1")
    List<ImageEntity> getListImageOfUser(long userId);

}
