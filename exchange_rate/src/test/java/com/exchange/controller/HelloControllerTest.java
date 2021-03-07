package com.exchange.controller;

import com.exchange.service.ExchangeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ExchangeService exchangeService;

	@Test
	public void Exception_test(){
	    // given
	    // when
	    // than
	}

}