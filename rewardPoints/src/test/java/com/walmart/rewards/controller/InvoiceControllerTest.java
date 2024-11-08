package com.walmart.rewards.controller;

import com.walmart.rewards.model.Invoice;
import com.walmart.rewards.model.Item;
import com.walmart.rewards.repository.CustomerDAO;
import com.walmart.rewards.repository.InvoiceDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class InvoiceControllerTest {

    @Autowired
    private InvoiceController invoiceController;

    @MockBean
    private InvoiceDAO invoiceDAO;

    @MockBean
    private CustomerDAO customerDAO;

    @Test
    void saveInvoiceHandlerTest() {
        Invoice invoice = new Invoice("001", LocalDate.parse("2024-06-10"), "001", "Rounak",
                Stream.of(
                                new Item("001", "Chips", 1, 20.0, 20.0),
                                new Item("002", "Chocolate", 2, 50.0, 100.0))
                        .collect(Collectors.toList()), 120.00);

        when(customerDAO.existsById(invoice.getCustomerId())).thenReturn(true);
        when(invoiceDAO.existsById(invoice.getInvoiceNumber())).thenReturn(false);
        when(invoiceDAO.save(invoice)).thenReturn(invoice);
        assertEquals(new ResponseEntity<>(invoice, HttpStatus.CREATED), invoiceController.saveInvoiceHandler(invoice));
    }

    @Test
    void getAllInvoicesHandlerTest() {
        List<Invoice> invoices = Stream.of(new Invoice("001", LocalDate.parse("2024-06-10"), "001", "Rounak",
                        Stream.of(
                                        new Item("001", "Chips", 1, 20.0, 20.0),
                                        new Item("002", "Chocolate", 2, 50.0, 100.0))
                                .collect(Collectors.toList()), 120.00),
                new Invoice("002", LocalDate.parse("2024-07-10"), "002", "Ravi",
                        Stream.of(
                                        new Item("001", "Chips", 1, 20.0, 20.0),
                                        new Item("002", "Chocolate", 2, 50.0, 100.0))
                                .collect(Collectors.toList()), 120.00),
                new Invoice("003", LocalDate.parse("2024-08-10"), "003", "Murali",
                        Stream.of(
                                        new Item("001", "Chips", 1, 20.0, 20.0),
                                        new Item("002", "Chocolate", 2, 50.0, 100.0))
                                .collect(Collectors.toList()), 120.00)).collect(Collectors.toList());

        when(invoiceDAO.findAll()).thenReturn(invoices);
        assertEquals(new ResponseEntity<>(invoices, HttpStatus.OK), invoiceController.getAllInvoicesHandler());
    }

    @Test
    void getInvoiceByInvoiceNumberHandlerTest() {
        String invoiceNumber = "001";
        Invoice invoice = new Invoice("001", LocalDate.parse("2024-06-10"), "001", "Rounak",
                Stream.of(
                                new Item("001", "Chips", 1, 20.0, 20.0),
                                new Item("002", "Chocolate", 2, 50.0, 100.0))
                        .collect(Collectors.toList()), 120.00);

        when(invoiceDAO.findById(invoiceNumber)).thenReturn(Optional.of(invoice));
        assertEquals(new ResponseEntity<>(invoice, HttpStatus.OK), invoiceController.getInvoiceByInvoiceNumberHandler(invoiceNumber));
    }

    @Test
    void updateInvoiceHandlerTest() {
        Invoice invoice = new Invoice("001", LocalDate.parse("2024-06-10"), "001", "Rounak",
                Stream.of(
                                new Item("001", "Chips", 1, 20.0, 20.0),
                                new Item("002", "Chocolate", 2, 50.0, 100.0))
                        .collect(Collectors.toList()), 120.00);

        when(invoiceDAO.existsById(invoice.getInvoiceNumber())).thenReturn(true);
        when(invoiceDAO.save(invoice)).thenReturn(invoice);
        assertEquals(new ResponseEntity<>(invoice, HttpStatus.OK), invoiceController.updateInvoiceHandler(invoice));
    }

    @Test
    void deleteInvoiceByInvoiceNumberHandlerTest() {
        String invoiceNumber = "001";

        when(invoiceDAO.existsById(invoiceNumber)).thenReturn(true);
        invoiceController.deleteInvoiceByInvoiceNumberHandler(invoiceNumber);
        verify(invoiceDAO, times(1)).deleteById(invoiceNumber);
    }
}
