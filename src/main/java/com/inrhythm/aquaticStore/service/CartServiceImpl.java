package com.inrhythm.aquaticStore.service;

import com.inrhythm.aquaticStore.model.entity.ShoppingCart;
import com.inrhythm.aquaticStore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepository repository;

    @Override
    public Iterable<ShoppingCart> getAllCarts() {
        return repository.findAll();
    }

    @Override
    public ShoppingCart findById(String id) {
        Optional<ShoppingCart> cartOptional = repository.findById(id);
        if(cartOptional.get() != null)
                return cartOptional.get();
        return null;
    }

    @Override
    public ShoppingCart save(ShoppingCart cart) {
        return repository.save(cart);
    }
}
