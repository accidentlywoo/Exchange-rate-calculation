package com.exchange.controller;

import com.exchange.data.dto.CalculateExchangeDto;
import com.exchange.data.dto.CalculateExchangeReqDto;
import com.exchange.data.dto.CurrentDto;
import com.exchange.data.dto.ExchangeDto;
import com.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/exchange")
@RestController
public class ExchangeController {
	private final ExchangeService exchangeService;

	@GetMapping("/{currency}")
	public ResponseEntity<ExchangeDto> getRate(@PathVariable String currency){
		return exchangeService.getCurrency(currency);
	}


	@PostMapping("/submit")
	public ResponseEntity<CalculateExchangeDto> exchange(@Validated @RequestBody CalculateExchangeReqDto reqDto){
		return exchangeService.convert(reqDto);
	}
}
