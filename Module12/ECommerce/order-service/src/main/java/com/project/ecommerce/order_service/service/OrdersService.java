package com.project.ecommerce.order_service.service;

import com.project.ecommerce.order_service.dto.OrderRequestDto;
import com.project.ecommerce.order_service.entity.Orders;
import com.project.ecommerce.order_service.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final ModelMapper modelMapper;

    public List<OrderRequestDto> getAllOrder() {
        log.info("Fetching all orders");
        List<Orders> ordersList = ordersRepository.findAll();
        return ordersList.stream()
                .map(orders -> modelMapper.map(orders, OrderRequestDto.class))
                .toList();
    }

    public OrderRequestDto getOrderById(Long id) {
        log.info("Fetching all orders");
        Optional<Orders> order = ordersRepository.findById(id);
        return order
                .map(orders -> modelMapper.map(orders, OrderRequestDto.class))
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
