package com.walmart.rewardPoints.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private String customerId;
    private String customerName;
    private List<Item> itemList;
    private Double invoiceAmount;

}
