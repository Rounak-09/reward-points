package com.walmart.rewardPoints.utility;

import com.walmart.rewardPoints.exception.UserException;
import com.walmart.rewardPoints.model.Customer;

import java.time.LocalDate;
import java.util.List;

public final class Validator {

    private Validator() {
    }

    private static void checkIfValidCustomerId(String customerId) {
        if (customerId == null || customerId.trim().equals("")) {
            throw new UserException("Please enter a valid customerId!");
        }
    }

    private static void checkIfValidCustomerName(String customerName) {
        if (customerName == null || customerName.trim().equals("")) {
            throw new UserException("Please enter a valid customer name!");
        }
    }

    private static void checkIfValidAddress(String address) {
        if (address == null || address.trim().equals("")) {
            throw new UserException("Please enter a valid address!");
        }
    }

    private static void checkIfValidPhoneNumber(String phone) {
        if (phone == null || phone.trim().equals("")) {
            throw new UserException("Please enter a valid phone number!");
        }
    }

    public static void validateCustomer(Customer customer) {
        if (customer==null) throw new UserException("Please provide customer details!");
        checkIfValidCustomerId(customer.getCustomerId());
        checkIfValidCustomerName(customer.getCustomerName());
        checkIfValidAddress(customer.getAddress());
        checkIfValidPhoneNumber(customer.getPhoneNumber());
    }



}
