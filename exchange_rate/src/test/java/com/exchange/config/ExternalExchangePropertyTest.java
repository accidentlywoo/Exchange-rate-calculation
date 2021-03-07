package com.exchange.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExternalExchangePropertyTest {
	@Autowired
	private ExternalExchangeProperty externalExchangeProperty;

	@Test
	public void configurationPropertyTest(){
		assertNotNull(externalExchangeProperty.getAccesskey());
		assertNotNull(externalExchangeProperty.getApiendpoint());
	}
}