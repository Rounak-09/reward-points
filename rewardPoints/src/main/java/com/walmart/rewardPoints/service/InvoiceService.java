package com.walmart.rewardpoints.service;

import com.walmart.rewardpoints.model.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice saveInvoice(Invoice invoice);

    List<Invoice> getAllInvoices();

    Invoice getInvoiceByInvoiceNumber(String invoiceNumber);

    Invoice updateInvoice(Invoice invoice);

    void deleteInvoiceByInvoiceNumber(String invoiceNumber);
}
