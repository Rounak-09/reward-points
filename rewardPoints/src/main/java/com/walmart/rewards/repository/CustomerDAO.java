package com.walmart.rewards.repository;

import com.walmart.rewards.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerDAO extends MongoRepository<Customer, String> {

}
