package com.walmart.rewardpoints.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyRewardPoints {

    private Month month;
    private Integer rewardPoints;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonthlyRewardPoints)) return false;
        MonthlyRewardPoints that = (MonthlyRewardPoints) o;
        return month == that.month && Objects.equals(rewardPoints, that.rewardPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, rewardPoints);
    }
}
