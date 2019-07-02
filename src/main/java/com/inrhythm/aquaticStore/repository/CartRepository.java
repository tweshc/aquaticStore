package com.inrhythm.aquaticStore.repository;

import com.inrhythm.aquaticStore.model.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<ShoppingCart, String> {
}
