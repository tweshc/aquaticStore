package com.inrhythm.aquaticStore.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ShoppingCart {
    List<Product> productsInCart = new ArrayList<>();
}
