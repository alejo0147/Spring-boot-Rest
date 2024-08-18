package com.algorian.application.rest.repository;

import com.algorian.application.rest.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    //  Anotación Query
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
    List<Product> findProductByInRange(BigDecimal minPrice, BigDecimal maxPrice);

    //  Query Method
    List<Product> findProductByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    Optional<Product> findByNameIgnoreCase(String name);
}
