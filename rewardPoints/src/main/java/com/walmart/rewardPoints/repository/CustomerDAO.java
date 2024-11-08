package com.walmart.rewardpoints.repository;

import com.walmart.rewardpoints.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerDAO extends MongoRepository<Customer, String> {

}
