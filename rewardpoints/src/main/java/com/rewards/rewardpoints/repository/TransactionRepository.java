package com.rewards.rewardpoints.repository;

import java.util.List;

import com.rewards.rewardpoints.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCustomerName(String customerName);
}


