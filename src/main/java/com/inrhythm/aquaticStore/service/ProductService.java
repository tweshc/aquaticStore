package com.inrhythm.aquaticStore.service;

import com.inrhythm.aquaticStore.model.entity.Product;
import com.inrhythm.aquaticStore.model.entity.ShoppingCart;
import org.springframework.ui.Model;

public interface ProductService {
    public ShoppingCart cart = new ShoppingCart();

    public Iterable<Product> getAllProducts();
    public Product findById (String id);
    public Product save(Product product);

    void addToCart(Product product, Model model);
}
