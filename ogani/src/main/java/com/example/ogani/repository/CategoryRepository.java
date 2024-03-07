package com.example.ogani.repository;

import com.example.ogani.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByNameContaining(String name);

    @Query("Select c from CategoryEntity c where c.enable = true")
    List<CategoryEntity> findAllByEnabled();

}
