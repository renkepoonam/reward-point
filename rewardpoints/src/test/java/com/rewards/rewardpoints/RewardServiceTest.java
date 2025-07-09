package com.rewards.rewardpoints;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rewards.rewardpoints.entity.Transaction;
import com.rewards.rewardpoints.service.RewardService;

@ExtendWith(MockitoExtension.class)
public class RewardServiceTest {

    @InjectMocks
    private RewardService rewardService;

    @Test
    public void testPointsBelow50() {
        assertEquals(0, rewardService.calculatePoints(45));
    }

    @Test
    public void testPointsExactly50() {
        assertEquals(0, rewardService.calculatePoints(50));
    }

    @Test
    public void testPointsBetween50And100() {
        assertEquals(25, rewardService.calculatePoints(75));
    }

    @Test
    public void testPointsExactly100() {
        assertEquals(50, rewardService.calculatePoints(100));
    }

    @Test
    public void testPointsAbove100() {
        assertEquals(90, rewardService.calculatePoints(120));
    }

    @Test
    public void testPointsExtremeAmount() {
        assertEquals(250, rewardService.calculatePoints(200));
    }

    @Test
    public void testMonthlyAggregation() {
        List<Transaction> transactions = List.of(
            new Transaction("Alice", LocalDate.of(2025, 7, 1), 120),
            new Transaction("Alice", LocalDate.of(2025, 7, 15), 80),
            new Transaction("Alice", LocalDate.of(2025, 8, 5), 200)
        );

        Map<String, Map<Month, Integer>> result = rewardService.calculateRewards(transactions);

        assertEquals(2, result.get("Alice").size());
        assertEquals(115, result.get("Alice").get(Month.JULY));
        assertEquals(250, result.get("Alice").get(Month.AUGUST));
    }
}
