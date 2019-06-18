package com.inrhythm.aquaticStore.repository;

import com.inrhythm.aquaticStore.model.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findById(String id);
}
