package com.inrhythm.aquaticStore.repository;

import com.inrhythm.aquaticStore.model.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

    Account findByUserName(String userName);
}
