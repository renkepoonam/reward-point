package com.rewards.rewardpoints;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.rewards.rewardpoints.entity.Transaction;
import com.rewards.rewardpoints.repository.TransactionRepository;
import com.rewards.rewardpoints.service.RewardService;

@TestPropertySource(properties = {
	    "CHITTER_JAVA_JWT_KEY=test"})
@SpringBootTest
@AutoConfigureMockMvc
public class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private RewardService rewardService;

    @Test
    public void testGetRewardsSuccess() throws Exception {
        List<Transaction> transactions = List.of(
            new Transaction("Bob", LocalDate.of(2025, 7, 10), 120)
        );

        Map<String, Map<Month, Integer>> mockRewards = Map.of(
            "Bob", Map.of(Month.JULY, 90)
        );

        when(transactionRepository.findAll()).thenReturn(transactions);
        when(rewardService.calculateRewards(transactions)).thenReturn(mockRewards);

        mockMvc.perform(get("/rewards"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.Bob.JULY").value(90));
    }

    @Test
    public void testInvalidEndpoint() throws Exception {
        mockMvc.perform(get("/rewardz"))
               .andExpect(status().isNotFound());
    }
}

