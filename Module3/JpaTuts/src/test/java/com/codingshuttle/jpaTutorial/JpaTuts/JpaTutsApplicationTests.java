package com.codingshuttle.jpaTutorial.JpaTuts;

import com.codingshuttle.jpaTutorial.JpaTuts.entities.ProductEntity;
import com.codingshuttle.jpaTutorial.JpaTuts.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class JpaTutsApplicationTests {
	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository() {
		ProductEntity productEntity = ProductEntity.builder()
				.sku("nestle")
				.title("nestle choco")
				.price(BigDecimal.valueOf(1234.23))
				.quantity(12)
				.build();
		ProductEntity savedProductEntity =
				productRepository.save(productEntity);
	}

	@Test
	void getRepository() {
        List<ProductEntity> entities = productRepository.findAll();
		System.out.println(entities);
	}

	@Test
	void getTitle() {
		List<ProductEntity> entities = productRepository.findByTitle("Pepsi");
		System.out.println(entities);
	}

	@Test
	void getEntriesAfter() {
		List<ProductEntity> entities = productRepository.findByCreatedAtBefore(LocalDateTime.of(2025,1,1,0,0,0));
	}

	@Test
	void getRepositoryOrder() {
		List<ProductEntity> entities = productRepository.findByTitleOrderByPriceAsc("Pepsi");
		System.out.println(entities);
	}


	@Test
	void getRepositoryAllOrder() {
		List<ProductEntity> entities = productRepository.findByOrderByPriceAsc();
		System.out.println(entities);
	}
}
