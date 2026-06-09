package com.codingshuttle.jpaTutorial.JpaTuts.controllers;

import com.codingshuttle.jpaTutorial.JpaTuts.entities.ProductEntity;
import com.codingshuttle.jpaTutorial.JpaTuts.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final Integer PAGE_SIZE = 5;

//    @GetMapping
//    public List<ProductEntity> getAllProducts() {
//        return productRepository.findByOrderByPriceAsc();
//    }

    @GetMapping
    public List<ProductEntity> getSortedProducts(@RequestParam(defaultValue = "id") String sortBy,
                                                 @RequestParam(defaultValue = "ASC") String sortOrder) {
        return productRepository.findBy(Sort.by(Sort.Direction.fromString(sortOrder),sortBy));
    }

    @GetMapping(path = "/page")
    public Page<ProductEntity> getAllProducts(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "ASC") String sortOrder
    ) {
        Pageable pageable = PageRequest.of(
                pageNumber, PAGE_SIZE,
                Sort.by(Sort.Direction.fromString(sortOrder),sortBy));
        return productRepository.findAll(pageable);
    }
}
