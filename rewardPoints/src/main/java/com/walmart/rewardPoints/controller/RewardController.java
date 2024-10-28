package com.walmart.rewardPoints.controller;

import com.walmart.rewardPoints.dto.CustomerRewardPoints;
import com.walmart.rewardPoints.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shopping")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping("/rewards")
    public ResponseEntity<List<CustomerRewardPoints>> getCustomerRewardPointsHandler(@RequestParam(required = false) String from, @RequestParam(required = false) String to) {
        List<CustomerRewardPoints> customerRewardPoints = rewardService.getCustomerRewardPoints(from, to);
        return new ResponseEntity<>(customerRewardPoints, HttpStatus.OK);
    }
}
