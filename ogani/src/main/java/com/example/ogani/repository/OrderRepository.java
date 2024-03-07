package com.example.ogani.repository;

import com.example.ogani.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "Select * from orders where user_id = :id order by id desc", nativeQuery = true)
    List<OrderEntity> getOrderByUser(long id);

}
