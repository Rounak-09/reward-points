package com.walmart.rewardpoints.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String itemNumber;
    private String itemName;
    private Integer quantity;
    private Double rate;
    private Double amount;

}
