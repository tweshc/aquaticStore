package com.inrhythm.aquaticStore.controller;

import com.inrhythm.aquaticStore.model.entity.ShoppingCart;
import com.inrhythm.aquaticStore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ShoppingCartController {

    @Autowired
    CartService service;

    @GetMapping(value = {"/carts/findAll"})
    public List<ShoppingCart> submitOrder(ShoppingCart cart){
        List<ShoppingCart> carts = new ArrayList<>();
        service.getAllCarts().forEach(carts::add);
        return carts;
    }
}
