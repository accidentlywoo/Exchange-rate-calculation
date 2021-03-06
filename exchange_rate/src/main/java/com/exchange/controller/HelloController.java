package com.exchange.controller;

import com.exchange.data.dto.CalculateExchangeDto;
import com.exchange.data.dto.CalculateExchangeReqDto;
import com.exchange.data.dto.CurrentDto;
import com.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HelloController {
	private final ExchangeService exchangeService;
	/**
	 * TODO
	 * 진입 후 /rate 호출할 지.
	 * 보통 오똑해하지?
	 * 주기적으로 갱신해서 최신 환율정보를 가져올지..
	 *
	 * @return
	 */
	@GetMapping("/")
	public String welcompage(Model model){
		model.addAttribute("reception",exchangeService.getReception());
		model.addAttribute("current",exchangeService.getCurrency("KRW"));
		return "index";
	}


	@GetMapping("/{currency}")
	public CurrentDto getRate(@PathVariable String currency){
		return exchangeService.getCurrency(currency);
	}


	@PostMapping("/submit")
	public CalculateExchangeDto exchange(@Validated @RequestBody CalculateExchangeReqDto reqDto){
		return exchangeService.convert(reqDto);
	}
}
