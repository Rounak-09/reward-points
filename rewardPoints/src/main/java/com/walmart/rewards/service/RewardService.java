package com.walmart.rewards.service;

import com.walmart.rewards.dto.CustomerRewardPoints;

import java.util.List;

public interface RewardService {

    List<CustomerRewardPoints> getCustomerRewardPoints(String from, String to);
}
