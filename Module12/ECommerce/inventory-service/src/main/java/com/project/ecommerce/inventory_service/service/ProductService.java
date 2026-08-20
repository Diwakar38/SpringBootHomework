package com.project.ecommerce.inventory_service.service;

import com.project.ecommerce.inventory_service.dto.OrderRequestDto;
import com.project.ecommerce.inventory_service.dto.OrderRequestItemDto;
import com.project.ecommerce.inventory_service.dto.ProductDto;
import com.project.ecommerce.inventory_service.entity.Product;
import com.project.ecommerce.inventory_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDto> getAllInventory() {
        log.info("Fetching all inventory items");
        List<Product> inventories = productRepository.findAll();
        return inventories.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    public ProductDto getProductById(Long id) {
        log.info("Fetching product by id: {}", id);
        Optional<Product> inventory = productRepository.findById(id);
        return inventory.map(product -> modelMapper.map(product, ProductDto.class))
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }

    @Transactional
    public Double reduceStocks(OrderRequestDto orderRequestDto) {
        log.info("Reducing the stocks");
        Double totalPrice = 0.0;
        for(OrderRequestItemDto dto : orderRequestDto.getItems()) {
            Long productId = dto.getProductId();
            Integer quantity = dto.getQuantity();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found with id " + productId));

            if(product.getStock() < quantity) {
                throw new RuntimeException("Not enough product in stock with id " + productId);
            }

            product.setStock(product.getStock() - quantity);
            productRepository.save(product);

            totalPrice += (quantity * product.getPrice());
        }
        return totalPrice;
    }
}
