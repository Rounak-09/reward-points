package com.walmart.rewardpoints.service;

import com.walmart.rewardpoints.dto.CustomerRewardPoints;

import java.util.List;

public interface RewardService {

    List<CustomerRewardPoints> getCustomerRewardPoints(String from, String to);
}
