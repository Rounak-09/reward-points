package com.walmart.rewardPoints.controller;

import com.walmart.rewardPoints.dto.CustomerRewardPoints;
import com.walmart.rewardPoints.exception.SystemException;
import com.walmart.rewardPoints.exception.UserException;
import com.walmart.rewardPoints.service.RewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(RewardController.class);

    @GetMapping("/rewards")
    public ResponseEntity<List<CustomerRewardPoints>> getCustomerRewardPointsHandler(@RequestParam(required = false) String from, @RequestParam(required = false) String to) {
        try {
            List<CustomerRewardPoints> customerRewardPoints = rewardService.getCustomerRewardPoints(from, to);
            return new ResponseEntity<>(customerRewardPoints, HttpStatus.OK);
        } catch (UserException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SystemException("Something went wrong, please try again later!");
        }

    }
}
