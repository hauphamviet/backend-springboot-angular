package com.example.ogani.repository;

import com.example.ogani.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {

    @Query(value = "Select * from BlogEntity order by id desc limit : limit", nativeQuery = true)
    List<BlogEntity> getListNewest(int limit);

}
