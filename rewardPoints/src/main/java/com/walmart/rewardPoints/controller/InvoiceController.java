package com.walmart.rewardPoints.controller;

import com.walmart.rewardPoints.exception.SystemException;
import com.walmart.rewardPoints.exception.UserException;
import com.walmart.rewardPoints.model.Invoice;
import com.walmart.rewardPoints.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/walmart")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @PostMapping("/invoices")
    public ResponseEntity<Invoice> saveInvoiceHandler(@RequestBody Invoice invoice) {
        try {
            Invoice response = invoiceService.saveInvoice(invoice);
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SystemException("Something went wrong, please try again later!");
        }

    }

    @GetMapping("/invoices")
    public ResponseEntity<List<Invoice>> getAllInvoicesHandler() {
        try {
            List<Invoice> invoiceList = invoiceService.getAllInvoices();
            return new ResponseEntity<>(invoiceList, HttpStatus.OK);
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SystemException("Something went wrong, please try again later!");
        }

    }

    @GetMapping("/invoices/{invoiceNumber}")
    public ResponseEntity<Invoice> getInvoiceByInvoiceNumberHandler(@PathVariable String invoiceNumber) {
        try {
            Invoice invoice = invoiceService.getInvoiceByInvoiceNumber(invoiceNumber);
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SystemException("Something went wrong, please try again later!");
        }

    }

    @PutMapping("/invoices")
    public ResponseEntity<Invoice> updateInvoiceHandler(@RequestBody Invoice invoice) {
        try {
            Invoice response = invoiceService.updateInvoice(invoice);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SystemException("Something went wrong, please try again later!");
        }

    }

    @DeleteMapping("/invoices/{invoiceNumber}")
    public ResponseEntity<String> deleteInvoiceByInvoiceNumberHandler(@PathVariable String invoiceNumber) {
        try {
            invoiceService.deleteInvoiceByInvoiceNumber(invoiceNumber);
            return new ResponseEntity<>("Invoice deleted successfully", HttpStatus.OK);
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SystemException("Something went wrong, please try again later!");
        }

    }


}
