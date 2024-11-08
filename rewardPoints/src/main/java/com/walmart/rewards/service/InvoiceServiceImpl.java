package com.walmart.rewards.service;

import com.walmart.rewards.exception.UserException;
import com.walmart.rewards.model.Invoice;
import com.walmart.rewards.repository.CustomerDAO;
import com.walmart.rewards.repository.InvoiceDAO;
import com.walmart.rewards.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceDAO invoiceDAO;

    private CustomerDAO customerDAO;

    @Autowired
    public InvoiceServiceImpl(InvoiceDAO invoiceDAO, CustomerDAO customerDAO) {
        this.invoiceDAO = invoiceDAO;
        this.customerDAO = customerDAO;
    }

    @Override
    public Invoice saveInvoice(Invoice invoice) {
        Validator.validateInvoice(invoice); // Validate data before adding it to the database

        if (invoiceDAO.existsById(invoice.getInvoiceNumber())) {
            throw new UserException("Invoice is already present with invoice number: "+invoice.getInvoiceNumber()); // Throw exception if invoice number already exists
        }
        if (!customerDAO.existsById(invoice.getCustomerId())) {
            throw new UserException("No customer found with customerId: "+invoice.getCustomerId()); // Throw exception if invalid customerId is provided in the invoice
        }
        return invoiceDAO.save(invoice);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceDAO.findAll(); // Get all invoices
    }

    @Override
    public Invoice getInvoiceByInvoiceNumber(String invoiceNumber) {
        if (invoiceNumber==null || invoiceNumber.trim().isEmpty()) {
            throw new UserException("Please provide invoice number!"); // Throw exception if invoice number is not provided
        }

        Optional<Invoice> optionalInvoice = invoiceDAO.findById(invoiceNumber);
        if (optionalInvoice.isPresent()) {
            return optionalInvoice.get();
        } else {
            throw new UserException("Invoice not found with invoice number: "+invoiceNumber); // Throw exception if invoice number provided is not found
        }
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Validator.validateInvoice(invoice); // Validate data before adding to the database

        if (invoiceDAO.existsById(invoice.getInvoiceNumber())) {
            return invoiceDAO.save(invoice);
        }
        throw new UserException("Invoice not found with invoice number: "+invoice.getInvoiceNumber()); // Throw exception if invoice number provided is invalid
    }

    @Override
    public void deleteInvoiceByInvoiceNumber(String invoiceNumber) {
        if (invoiceNumber==null || invoiceNumber.trim().isEmpty()) {
            throw new UserException("Please provide invoice number!"); // Throw exception if invoice number is not provided
        }

        if (invoiceDAO.existsById(invoiceNumber)) {
            invoiceDAO.deleteById(invoiceNumber);
        } else {
            throw new UserException("Invoice not found with invoice number: "+invoiceNumber); // Throw exception if invoice number provided is invalid
        }
    }
}
