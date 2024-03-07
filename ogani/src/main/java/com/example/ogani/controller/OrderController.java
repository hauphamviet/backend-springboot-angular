package com.example.ogani.controller;

import com.example.ogani.entity.OrderEntity;
import com.example.ogani.model.request.OrderRequest;
import com.example.ogani.model.response.MessageResponse;
import com.example.ogani.model.response.OrderResponse;
import com.example.ogani.service.orders.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/")
    @Operation(summary = "Lay ra danh sach dat hang")
    public ResponseEntity<List<OrderResponse>> getList() {
        List<OrderResponse> list = orderService.getList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/user")
    @Operation(summary = "Lay ra danh sach dat hang cua nguoi dung bang username")
    public ResponseEntity<List<OrderEntity>> getListByUser(@RequestParam("username") String username) {
        List<OrderEntity> list = orderService.getOrderByUser(username);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    @Operation(summary = "Dat hang san pham")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return ResponseEntity.ok(new MessageResponse("Order Placed Successfully!"));
    }

}
