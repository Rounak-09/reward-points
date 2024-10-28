package com.walmart.rewardPoints.service;


import com.walmart.rewardPoints.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    void deleteCustomerById(String customerId);

    Customer saveCustomer(Customer customer);

    Customer getCustomerById(String customerId);

    Customer updateCustomer(Customer customer);
}
