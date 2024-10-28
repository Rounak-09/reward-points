package com.walmart.rewardPoints.utility;

import com.walmart.rewardPoints.exception.UserException;
import com.walmart.rewardPoints.model.Customer;
import com.walmart.rewardPoints.model.Invoice;
import com.walmart.rewardPoints.model.Item;

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

    private static void checkIfValidInvoiceNumber(String invoiceNumber) {
        if (invoiceNumber == null || invoiceNumber.trim().equals("")) {
            throw new UserException("Please enter a valid invoice number!");
        }
    }

    private static void checkIfValidMonth(LocalDate date) {
        if (date == null || date.getMonth() == null) {
            throw new UserException("Please enter a valid date!");
        }
    }

    private static void checkIfValidItemNumber(String itemNumber) {
        if (itemNumber == null || itemNumber.trim().equals("")) {
            throw new UserException("Please enter a valid item number!");
        }
    }

    private static void checkIfValidItemName(String itemName) {
        if (itemName == null || itemName.trim().equals("")) {
            throw new UserException("Please enter a valid item name!");
        }
    }

    private static void checkIfValidQuantity(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new UserException("Please enter a valid quantity!");
        }
    }

    private static void checkIfValidRate(Double rate) {
        if (rate == null || rate <= 0) {
            throw new UserException("Please enter a valid rate!");
        }
    }

    public static void validateInvoice(Invoice invoice) {
        if (invoice == null) {
            throw new UserException("Please provide invoice details!");
        }

        checkIfValidCustomerId(invoice.getCustomerId());
        checkIfValidCustomerName(invoice.getCustomerName());
        checkIfValidInvoiceNumber(invoice.getInvoiceNumber());
        checkIfValidMonth(invoice.getInvoiceDate());
        checkIfValidItems(invoice.getItemList());

        double invoiceAmount = invoice.getItemList().stream()
                .mapToDouble(Item::getAmount)
                .sum();

        invoice.setInvoiceAmount(Math.floor(invoiceAmount) * 100 / 100);
    }

    public static void validateInvoices(List<Invoice> invoices) {
        if (invoices == null || invoices.isEmpty()) {
            throw new UserException("Please provide invoice details!");
        }

        for (Invoice invoice: invoices) {
            validateInvoice(invoice);
        }
    }

    private static void checkIfValidItems(List<Item> items) {
        if (items==null || items.isEmpty()) throw new UserException("Please provide item details!");
        for (Item item: items) {
            if (item==null) throw new UserException("Please provide item details!");
            checkIfValidItemNumber(item.getItemNumber());
            checkIfValidItemName(item.getItemName());
            checkIfValidQuantity(item.getQuantity());
            checkIfValidRate(item.getRate());
            item.setAmount(Math.floor(item.getQuantity() * item.getRate()) * 100 / 100);
        }
    }

}
