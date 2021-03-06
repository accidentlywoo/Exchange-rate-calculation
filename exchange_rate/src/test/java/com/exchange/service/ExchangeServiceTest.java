package com.exchange.service;

import com.exchange.ExchangeRateApplicationTests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class ExchangeServiceTest extends ExchangeRateApplicationTests {

	@Autowired
	private ExchangeService exchangeService;

	@Test
	public void  프로퍼티_머선129(){
	    // given
//		System.out.println(exchangeService.ENDPOINT);
//		System.out.println(exchangeService.ACCESS_KEY);

	    // when
	    // than
	}
	@Test
	public void isLive(){
	    // given
	    // when
	    // than
	}
	@Test
	public void 출력_테스트(){
	    // given
	    // when
	    // than
	}

}