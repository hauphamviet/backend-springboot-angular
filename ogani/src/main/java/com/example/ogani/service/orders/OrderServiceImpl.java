package com.example.ogani.service.orders;

import com.example.ogani.common.Constants;
import com.example.ogani.entity.OrderDetailEntity;
import com.example.ogani.entity.OrderEntity;
import com.example.ogani.exception.NotFoundException;
import com.example.ogani.model.request.OrderDetailRequest;
import com.example.ogani.model.request.OrderRequest;
import com.example.ogani.model.response.OrderResponse;
import com.example.ogani.repository.OrderDetailRepository;
import com.example.ogani.repository.OrderRepository;
import com.example.ogani.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderResponse> getList() {
        var orderList = orderRepository.findAll(Sort.by("id"));
        return orderList.stream().map(this::mapToOrderResponse).toList();
    }

    private OrderResponse mapToOrderResponse(OrderEntity orderEntity) {
        return OrderResponse.builder()
                .id(orderEntity.getId())
                .firstname(orderEntity.getFirstname())
                .lastname(orderEntity.getLastname())
                .country(orderEntity.getCountry())
                .address(orderEntity.getAddress())
                .town(orderEntity.getTown())
                .state(orderEntity.getState())
                .postCode(orderEntity.getPostCode())
                .email(orderEntity.getEmail())
                .phone(orderEntity.getPhone())
                .note(orderEntity.getNote())
                .totalPrice(orderEntity.getTotalPrice())
                .user(orderEntity.getUser())
                .orderDetails(orderEntity.getOrderdetails())
                .build();
    }

    @Override
    public List<OrderEntity> getOrderByUser(String username) {
        var userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(Constants.USER_NAME_NOT_EXIST + username));
        return orderRepository.getOrderByUser(userEntity.getId());
    }

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        var orderEntity = new OrderEntity();
        var userEntity = userRepository.findByUsername(orderRequest.getUsername())
                .orElseThrow(() -> new NotFoundException(Constants.USER_NAME_NOT_EXIST + orderRequest.getUsername()));
        orderEntity.setFirstname(orderRequest.getFirstname());
        orderEntity.setLastname(orderRequest.getLastname());
        orderEntity.setCountry(orderRequest.getCountry());
        orderEntity.setAddress(orderRequest.getAddress());
        orderEntity.setTown(orderRequest.getTown());
        orderEntity.setState(orderRequest.getState());
        orderEntity.setPostCode(orderRequest.getPostCode());
        orderEntity.setEmail(orderRequest.getEmail());
        orderEntity.setPhone(orderRequest.getPhone());
        orderEntity.setNote(orderRequest.getNote());
        orderRepository.save(orderEntity);
        long totalPrice = 0;
        for (OrderDetailRequest rq: orderRequest.getOrderDetails()) {
            var orderDetail = new OrderDetailEntity();
            orderDetail.setName(rq.getName());
            orderDetail.setPrice(rq.getPrice());
            orderDetail.setQuantity(rq.getQuantity());
            orderDetail.setSubTotal(rq.getPrice() * rq.getQuantity());
            orderDetail.setOrder(orderEntity);
            totalPrice += orderDetail.getSubTotal();
            orderDetailRepository.save(orderDetail);
        }
        orderEntity.setTotalPrice(totalPrice);
        orderEntity.setUser(userEntity);
        orderRepository.save(orderEntity);
    }
}
