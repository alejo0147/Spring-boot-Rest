package com.algorian.application.rest.persistence.impl;

import com.algorian.application.rest.entities.Product;
import com.algorian.application.rest.persistence.IProductDAO;
import com.algorian.application.rest.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ProductDAOImpl implements IProductDAO {

    private final IProductRepository _productRepository;

    @Override
    public List<Product> findAll() {
        return _productRepository.findAll();
    }

    @Override
    public List<Product> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return _productRepository.findProductByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return _productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        _productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        _productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return _productRepository.findByNameIgnoreCase(name);
    }
}
