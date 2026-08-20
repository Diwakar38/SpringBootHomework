package com.project.ecommerce.order_service.controller;

import com.project.ecommerce.order_service.client.InventoryOpenFeignClient;
import com.project.ecommerce.order_service.dto.OrderRequestDto;
import com.project.ecommerce.order_service.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/core")
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping("/hello")
    public String hello(@RequestHeader("X-User-Id") Long userId) {
        return "Hello from Order Service, with user id: " + userId;
    }

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders() {
        log.info("Fetching all the orders");
        List<OrderRequestDto> orders = ordersService.getAllOrder();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id) {
        log.info("Fetching order with id: {}", id);
        OrderRequestDto orders = ordersService.getOrderById(id);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/create-order")
    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderRequestDto orderRequestDto1 = ordersService.createOrder(orderRequestDto);
        return ResponseEntity.ok(orderRequestDto1);
    }
}
