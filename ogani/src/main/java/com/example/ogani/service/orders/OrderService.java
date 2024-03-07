package com.example.ogani.service.orders;

import com.example.ogani.entity.OrderEntity;
import com.example.ogani.model.request.OrderRequest;
import com.example.ogani.model.response.OrderResponse;

import java.util.List;

public interface OrderService {

    void placeOrder(OrderRequest orderRequest);

    List<OrderResponse> getList();

    List<OrderEntity> getOrderByUser(String username);

}
