package com.example.ogani.repository;

import com.example.ogani.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "Select * from products order by price limit 8 ", nativeQuery = true)
    List<ProductEntity> getListByPrice();

    @Query(value = "Select * from products where category_id = :id", nativeQuery = true)
    List<ProductEntity> getListProductByCategory(long id);

    @Query(value = "Select p from ProductEntity p where p.name like %:keyword% order by id desc")
    List<ProductEntity> searchProduct(String keyword);

    @Query(value = "Select * from products where category_id = :id and price between :min and :max", nativeQuery = true)
    List<ProductEntity> getListProductByPriceRange(long id, int min, int max);

    @Query(value = "Select * from products where category_id = :id order by rand() limit 4", nativeQuery = true)
    List<ProductEntity> findRelatedProduct(long id);

    @Query(value = "Select * from products order by id desc limit :number", nativeQuery = true)
    List<ProductEntity> getListNewest(int number);

}
