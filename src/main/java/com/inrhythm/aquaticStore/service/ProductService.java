package com.inrhythm.aquaticStore.service;

import com.inrhythm.aquaticStore.model.Product;
import com.inrhythm.aquaticStore.model.ShoppingCart;
import org.springframework.ui.Model;

import java.util.List;

public interface ProductService {
    public ShoppingCart cart = new ShoppingCart();

    Iterable<Product> getAllProducts();
    Product findById (String id);
    Product save(Product product);

    void addToCart(Product product, Model model);
    List<Product> filterProductsDouble(String field, String filter, Double amount);

    List<Product> filterProductsString(String field, String value);
}
