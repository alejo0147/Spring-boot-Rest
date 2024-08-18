package com.algorian.application.rest.service.impl;

import com.algorian.application.rest.entities.Product;
import com.algorian.application.rest.persistence.IProductDAO;
import com.algorian.application.rest.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceimpl implements IProductService {

    private final IProductDAO _productDAO;

    @Override
    public List<Product> findAll() {
        return _productDAO.findAll();
    }

    @Override
    public List<Product> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return _productDAO.findByPriceInRange(minPrice, maxPrice);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return _productDAO.findById(id);
    }

    @Override
    public void save(Product product) {
        _productDAO.save(product);
    }

    @Override
    public void deleteById(Long id) {
        _productDAO.deleteById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return _productDAO.findByName(name);
    }
}
