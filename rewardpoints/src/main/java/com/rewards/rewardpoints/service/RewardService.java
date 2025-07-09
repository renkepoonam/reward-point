package com.rewards.rewardpoints.service;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rewards.rewardpoints.entity.*;
import org.springframework.stereotype.Service;

@Service
public class RewardService {

    public int calculatePoints(double amount) {
        if (amount > 100) return (int)((amount - 100) * 2 + 50);
        else if (amount > 50) return (int)(amount - 50);
        else return 0;
    }

    public Map<String, Map<Month, Integer>> calculateRewards(List<Transaction> transactions) {
        Map<String, Map<Month, Integer>> rewardSummary = new HashMap<>();

        for (Transaction tx : transactions) {
            String name = tx.getCustomerName();
            Month month = tx.getTransactionDate().getMonth();
            int points = calculatePoints(tx.getAmount());

            rewardSummary
                .computeIfAbsent(name, k -> new HashMap<>())
                .merge(month, points, Integer::sum);
        }

        return rewardSummary;
    }
}

