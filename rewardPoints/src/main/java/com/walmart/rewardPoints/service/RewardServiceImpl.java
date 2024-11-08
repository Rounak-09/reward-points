package com.walmart.rewardpoints.service;

import com.walmart.rewardpoints.dto.CustomerRewardPoints;
import com.walmart.rewardpoints.helper.RewardCalculator;
import com.walmart.rewardpoints.model.Invoice;
import com.walmart.rewardpoints.repository.InvoiceDAO;
import com.walmart.rewardpoints.utility.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RewardServiceImpl implements RewardService {

    private InvoiceDAO invoiceDAO;

    private RewardCalculator rewardCalculator;

    @Autowired
    public RewardServiceImpl(InvoiceDAO invoiceDAO, RewardCalculator rewardCalculator) {
        this.invoiceDAO = invoiceDAO;
        this.rewardCalculator = rewardCalculator;
    }

    @Override
    public List<CustomerRewardPoints> getCustomerRewardPoints(String from, String to) {
        LocalDate fromDate = Converter.convertToLocalDate(from);
        LocalDate toDate = Converter.convertToLocalDate(to);
        List<Invoice> invoices = (fromDate == null || toDate == null)
                ? invoiceDAO.getByInvoiceDateBetween(LocalDate.now().minusMonths(3).minusDays(1), LocalDate.now().plusDays(1)) //Last 3 months data is considered if no date range is provided
                : invoiceDAO.getByInvoiceDateBetween(fromDate.minusDays(1), toDate.plusDays(1)); //1 day is subtracted and added to the date range as between excludes the from and to date
        return rewardCalculator.calculate(invoices);
    }
}
