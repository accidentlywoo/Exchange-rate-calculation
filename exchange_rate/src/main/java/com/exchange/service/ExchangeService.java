package com.exchange.service;

import com.exchange.data.dto.CalculateExchangeDto;
import com.exchange.data.dto.CalculateExchangeReqDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {
	// https://currencylayer.com/quickstart

	@Value("currency.accessKey")
	private String ACCESS_KEY;

	@Value("currency.endpoint")
	private String ENDPOINT;

	public ResponseEntity<String> getCurrency(String currency) {
		return new ResponseEntity<String>("", HttpStatus.OK);
	}

	public ResponseEntity<CalculateExchangeDto> convert(CalculateExchangeReqDto reqDto) {
		return new ResponseEntity<CalculateExchangeDto>(new CalculateExchangeDto(), HttpStatus.OK);
	}
}
