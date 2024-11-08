package com.walmart.rewards.service;

import com.walmart.rewards.model.Customer;
import com.walmart.rewards.repository.CustomerDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerDAO customerDAO;

    @Test
    void saveCustomerTest() {
        Customer customer = new Customer("001", "Rounak", "7978881737", "Bhubaneswar");

        when(customerDAO.save(customer)).thenReturn(customer);
        assertEquals(customer, customerService.saveCustomer(customer));
    }

    @Test
    void getAllCustomersTest() {
        List<Customer> customers = Stream.of(
                new Customer("001", "Rounak", "7978881737", "Bhubaneswar"),
                new Customer("002", "Ravi", "7978881738", "Bangalore"),
                new Customer("003", "Murali", "7978881739", "Chennai")
        ).collect(Collectors.toList());

        when(customerDAO.findAll()).thenReturn(customers);
        assertEquals(customers, customerService.getAllCustomers());
    }

    @Test
    void getCustomerByIdTest() {
        String customerId = "001";
        Customer customer = new Customer("001", "Rounak", "7978881737", "Bhubaneswar");

        when(customerDAO.findById(customerId)).thenReturn(Optional.of(customer));
        assertEquals(customer, customerService.getCustomerById(customerId));
    }

    @Test
    void updateCustomerTest() {
        Customer customer = new Customer("001", "Rounak", "7978881737", "Bhubaneswar");

        when(customerDAO.existsById(customer.getCustomerId())).thenReturn(true);
        when(customerDAO.save(customer)).thenReturn(customer);
        assertEquals(customer, customerService.updateCustomer(customer));
    }

    @Test
    void deleteCustomerByIdTest() {
        String customerId = "001";

        when(customerDAO.existsById(customerId)).thenReturn(true);
        customerService.deleteCustomerById(customerId);
        verify(customerDAO, times(1)).deleteById(customerId);
    }
}
