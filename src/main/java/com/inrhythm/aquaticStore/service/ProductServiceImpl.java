package com.inrhythm.aquaticStore.service;

import com.inrhythm.aquaticStore.model.entity.Product;
import com.inrhythm.aquaticStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository repository;

    @Override
    public Iterable<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product findById(String id) {
        Optional<Product> prod = repository.findById(id);
        if(prod.get() != null)
            return prod.get();
        return null;
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }
}
