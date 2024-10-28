package com.walmart.rewardPoints.repository;

import com.walmart.rewardPoints.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerDAO extends MongoRepository<Customer, String> {

}
