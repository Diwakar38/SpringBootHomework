package com.codingshuttle.jpaTutorial.JpaTuts.repositories;

import com.codingshuttle.jpaTutorial.JpaTuts.entities.ProductEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByTitle(String pepsi);
    List<ProductEntity> findByCreatedAtAfter(LocalDateTime after);
    List<ProductEntity> findByCreatedAtBefore(LocalDateTime before);
    List<ProductEntity> findByTitleOrderByPriceAsc(String title);
    List<ProductEntity> findByOrderByPriceAsc();
    List<ProductEntity> findBy(Sort sortBy);
}
