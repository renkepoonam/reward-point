package com.rewards.rewardpoints.controller;

import java.time.Month;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.rewardpoints.entity.Transaction;
import com.rewards.rewardpoints.repository.TransactionRepository;
import com.rewards.rewardpoints.service.RewardService;

@RestController
@RequestMapping("/rewards")
public class RewardController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RewardService rewardService;

    @GetMapping
    public ResponseEntity<Map<String, Map<Month, Integer>>> getRewards() {
        List<Transaction> transactions = transactionRepository.findAll();
        Map<String, Map<Month, Integer>> rewards = rewardService.calculateRewards(transactions);
        return ResponseEntity.ok(rewards);
    }
}

