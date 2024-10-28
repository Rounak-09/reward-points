package com.walmart.rewardPoints.service;

import com.walmart.rewardPoints.model.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice saveInvoice(Invoice invoice);

    List<Invoice> getAllInvoices();

    Invoice getInvoiceByInvoiceNumber(String invoiceNumber);

    Invoice updateInvoice(Invoice invoice);

    void deleteInvoiceByInvoiceNumber(String invoiceNumber);
}
