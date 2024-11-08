package com.walmart.rewardpoints.controller;

import com.walmart.rewardpoints.model.Customer;
import com.walmart.rewardpoints.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> saveCustomerHandler(@RequestBody Customer customer) {
        Customer response = customerService.saveCustomer(customer);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomersHandler() {
        List<Customer> customerList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Customer> getCustomerByIdHandler(@PathVariable String customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/customers")
    public ResponseEntity<Customer> updateCustomerHandler(@RequestBody Customer customer) {
        Customer response = customerService.updateCustomer(customer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<String> deleteCustomerByIdHandler(@PathVariable String customerId) {
        customerService.deleteCustomerById(customerId);
        return new ResponseEntity<>("Customers deleted successfully", HttpStatus.OK);
    }
}
