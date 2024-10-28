package com.walmart.rewardPoints.service;

import com.walmart.rewardPoints.exception.UserException;
import com.walmart.rewardPoints.model.Customer;
import com.walmart.rewardPoints.repository.CustomerDAO;
import com.walmart.rewardPoints.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    @Override
    public void deleteCustomerById(String customerId) {
        if (customerId==null || customerId.trim().isEmpty()) {
            throw new UserException("Please provide customer details!"); // Handling edge cases by throwing exception if customerId is not provided
        }

        if (customerDAO.existsById(customerId)) {
            customerDAO.deleteById(customerId);
        } else {
            throw new UserException("Customer not found with customerId: "+customerId); // Check if customerId provided is valid then proceed or else throw exception
        }
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        Validator.validateCustomer(customer); // Validate the data before adding it to the database

        if (customerDAO.existsById(customer.getCustomerId())) {
            throw new UserException("Customer is already present with customerId: "+customer.getCustomerId()); // Check if customerId already exists to prevent overwriting
        }
        return customerDAO.save(customer);
    }

    @Override
    public Customer getCustomerById(String customerId) {
        if (customerId==null || customerId.trim().isEmpty()) {
            throw new UserException("Please provide customerId!"); // Handling edge cases by throwing exception if customerId is not provided
        }

        Optional<Customer> optionalCustomer = customerDAO.findById(customerId);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new UserException("Customer not found with customerId: "+customerId); // Get data if customerId is present or else throw exception
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Validator.validateCustomer(customer); // Validate the data before adding it to the database

        int i = 1/0;
        if (customerDAO.existsById(customer.getCustomerId())) {
            return customerDAO.save(customer);
        }
        throw new UserException("Customer not found with customerId: "+customer.getCustomerId()); // Update data if customerId exists or else throw exception
    }

}
