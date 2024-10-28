package com.walmart.rewardPoints.controller;

import com.walmart.rewardPoints.model.Invoice;
import com.walmart.rewardPoints.service.InvoiceService;
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

    @PostMapping("/invoices")
    public ResponseEntity<Invoice> saveInvoiceHandler(@RequestBody Invoice invoice) {
        Invoice response = invoiceService.saveInvoice(invoice);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/invoices")
    public ResponseEntity<List<Invoice>> getAllInvoicesHandler() {
        List<Invoice> invoiceList = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoiceList, HttpStatus.OK);
    }

    @GetMapping("/invoices/{invoiceNumber}")
    public ResponseEntity<Invoice> getInvoiceByInvoiceNumberHandler(@PathVariable String invoiceNumber) {
        Invoice invoice = invoiceService.getInvoiceByInvoiceNumber(invoiceNumber);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @PutMapping("/invoices")
    public ResponseEntity<Invoice> updateInvoiceHandler(@RequestBody Invoice invoice) {
        Invoice response = invoiceService.updateInvoice(invoice);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/invoices/{invoiceNumber}")
    public ResponseEntity<String> deleteInvoiceByInvoiceNumberHandler(@PathVariable String invoiceNumber) {
        invoiceService.deleteInvoiceByInvoiceNumber(invoiceNumber);
        return new ResponseEntity<>("Invoice deleted successfully", HttpStatus.OK);
    }

}
