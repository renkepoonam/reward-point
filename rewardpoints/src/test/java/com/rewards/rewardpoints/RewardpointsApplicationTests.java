package com.rewards.rewardpoints;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {
"CHITTER_JAVA_JWT_KEY=test"})
@SpringBootTest
class RewardpointsApplicationTests {

	@Test
	public void contextLoads() {
		String test = "test";
		assertEquals("test", test);
	}

}
