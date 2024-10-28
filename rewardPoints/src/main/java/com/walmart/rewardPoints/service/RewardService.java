package com.walmart.rewardPoints.service;

import com.walmart.rewardPoints.dto.CustomerRewardPoints;

import java.util.List;

public interface RewardService {

    List<CustomerRewardPoints> getCustomerRewardPoints(String from, String to);
}
