package com.walmart.rewards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRewardPoints {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerRewardPoints)) return false;
        CustomerRewardPoints that = (CustomerRewardPoints) o;
        return Objects.equals(customerId, that.customerId)
                && Objects.equals(customerName, that.customerName)
                && (monthlyRewardPointsList.size() == that.monthlyRewardPointsList.size()
                && monthlyRewardPointsList.containsAll(that.monthlyRewardPointsList)
                && that.monthlyRewardPointsList.containsAll(monthlyRewardPointsList))
                && Objects.equals(totalRewardPoints, that.totalRewardPoints);
    }

    @Override
    public int hashCode() {

        return Objects.hash(customerId, customerName, monthlyRewardPointsList, totalRewardPoints);
    }

    private String customerId;
    private String customerName;
    private List<MonthlyRewardPoints> monthlyRewardPointsList;
    private Integer totalRewardPoints;

}
