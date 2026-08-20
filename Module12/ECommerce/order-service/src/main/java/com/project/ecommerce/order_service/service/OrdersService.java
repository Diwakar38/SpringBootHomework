package com.project.ecommerce.order_service.service;

import com.project.ecommerce.order_service.client.InventoryOpenFeignClient;
import com.project.ecommerce.order_service.dto.OrderRequestDto;
import com.project.ecommerce.order_service.entity.OrderItem;
import com.project.ecommerce.order_service.entity.OrderStatus;
import com.project.ecommerce.order_service.entity.Orders;
import com.project.ecommerce.order_service.repository.OrdersRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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
    private final InventoryOpenFeignClient inventoryOpenFeignClient;

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

//    @Retry(name = "inventoryRetry", fallbackMethod = "createOrderFallback")
    @CircuitBreaker(name = "inventoryCircuitBreaker", fallbackMethod = "createOrderFallback")
    @RateLimiter(name = "inventoryRateLimiter", fallbackMethod = "createOrderFallback")
    public OrderRequestDto createOrder(OrderRequestDto orderRequestDto) {
        log.info("Calling the createOrder method");
        Double totalPrice = inventoryOpenFeignClient.reduceStocks(orderRequestDto);
        Orders orders = modelMapper.map(orderRequestDto, Orders.class);
        orders.setTotalPrice(totalPrice);
        for(OrderItem orderItem : orders.getOrderItems()) {
            orderItem.setOrders(orders);
        }
        orders.setOrderStatus(OrderStatus.CONFIRMED);
        ordersRepository.save(orders);
        return modelMapper.map(orders, OrderRequestDto.class);
    }

    public OrderRequestDto createOrderFallback(OrderRequestDto orderRequestDto, Throwable throwable) {
        log.error("OrderResponseFallback occured due to :{}", throwable.getMessage());

        return new OrderRequestDto();
    }

}
