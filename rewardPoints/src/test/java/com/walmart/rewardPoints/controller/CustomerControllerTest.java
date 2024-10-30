package com.walmart.rewardPoints.controller;

import com.walmart.rewardPoints.model.Customer;
import com.walmart.rewardPoints.repository.CustomerDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerControllerTest {

    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerDAO customerDAO;

    @Test
    void saveCustomerHandlerTest() {
        Customer customer = new Customer("001", "Rounak", "7978881737", "Bhubaneswar");

        when(customerDAO.save(customer)).thenReturn(customer);
        assertEquals(new ResponseEntity<>(customer, HttpStatus.CREATED), customerController.saveCustomerHandler(customer));
    }

    @Test
    void getAllCustomersHandlerTest() {
        List<Customer> customers = Stream.of(
                new Customer("001", "Rounak", "7978881737", "Bhubaneswar"),
                new Customer("002", "Ravi", "7978881738", "Bangalore"),
                new Customer("003", "Murali", "7978881739", "Chennai")
        ).collect(Collectors.toList());

        when(customerDAO.findAll()).thenReturn(customers);
        assertEquals(new ResponseEntity<>(customers, HttpStatus.OK), customerController.getAllCustomersHandler());
    }

    @Test
    void getCustomerByIdHandlerTest() {
        String customerId = "001";
        Customer customer = new Customer(customerId, "Rounak", "7978881737", "Bhubaneswar");

        when(customerDAO.findById(customerId)).thenReturn(Optional.of(customer));
        assertEquals(new ResponseEntity<>(customer, HttpStatus.OK), customerController.getCustomerByIdHandler(customerId));
    }

    @Test
    void updateCustomerHandlerTest() {
        Customer customer = new Customer("001", "Rounak", "7978881737", "Bhubaneswar");

        when(customerDAO.existsById(customer.getCustomerId())).thenReturn(true);
        when(customerDAO.save(customer)).thenReturn(customer);
        assertEquals(new ResponseEntity<>(customer, HttpStatus.OK), customerController.updateCustomerHandler(customer));
    }

    @Test
    void deleteCustomerByIdHandlerTest() {
        String customerId = "001";

        when(customerDAO.existsById(customerId)).thenReturn(true);
        customerController.deleteCustomerByIdHandler(customerId);
        verify(customerDAO, times(1)).deleteById(customerId);
    }

}
