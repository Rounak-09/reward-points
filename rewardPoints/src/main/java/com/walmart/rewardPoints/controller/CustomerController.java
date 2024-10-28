package com.walmart.rewardPoints.controller;

import com.walmart.rewardPoints.exception.SystemException;
import com.walmart.rewardPoints.exception.UserException;
import com.walmart.rewardPoints.model.Customer;
import com.walmart.rewardPoints.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping("/customers")
    public ResponseEntity<Customer> saveCustomerHandler(@RequestBody Customer customer) {
        try {
            Customer response = customerService.saveCustomer(customer);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SystemException("Something went wrong, please try again later!");
        }

    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomersHandler() {
        try {
            List<Customer> customerList = customerService.getAllCustomers();
            return new ResponseEntity<>(customerList, HttpStatus.OK);
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SystemException("Something went wrong, please try again later!");
        }

    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Customer> getCustomerByIdHandler(@PathVariable String customerId) {
        try {
            Customer customer = customerService.getCustomerById(customerId);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SystemException("Something went wrong, please try again later!");
        }

    }

    @PutMapping("/customers")
    public ResponseEntity<Customer> updateCustomerHandler(@RequestBody Customer customer) {
        try {
            Customer response = customerService.updateCustomer(customer);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SystemException("Something went wrong, please try again later!");
        }

    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<String> deleteCustomerByIdHandler(@PathVariable String customerId) {
        try {
            customerService.deleteCustomerById(customerId);
            return new ResponseEntity<>("Customers deleted successfully", HttpStatus.OK);
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SystemException("Something went wrong, please try again later!");
        }

    }
}
