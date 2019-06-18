package com.inrhythm.aquaticStore.service;

import com.inrhythm.aquaticStore.model.entity.Product;

public interface ProductService {
    public Iterable<Product> getAllProducts();
    public Product findById (String id);
    public Product save(Product product);
}
