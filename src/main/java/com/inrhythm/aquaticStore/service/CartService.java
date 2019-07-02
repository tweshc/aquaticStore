package com.inrhythm.aquaticStore.service;

import com.inrhythm.aquaticStore.model.ShoppingCart;

public interface CartService {
    public Iterable<ShoppingCart> getAllCarts();
    public ShoppingCart findById (String id);
    public ShoppingCart save(ShoppingCart product);
}
