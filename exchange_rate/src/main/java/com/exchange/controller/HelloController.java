package com.exchange.controller;

import com.exchange.data.dto.CalculateExchangeDto;
import com.exchange.data.dto.CalculateExchangeReqDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
	/**
	 * TODO
	 * 진입 후 /rate 호출할 지.
	 * 보통 오똑해하지?
	 * 주기적으로 갱신해서 최신 환율정보를 가져올지..
	 *
	 * @return
	 */
	@GetMapping("/")
	public String welcompage(){
		return "index";
	}

	/**
	 * TODO
	 * Select 박스에서 수취국가 선택시 호출
	 *
	 * @return
	 */
	@GetMapping("/rate")
	public String getRate(){
		return "index";
	}

	/**
	 * TODO
	 *
	 *
	 * @param reqDto
	 * @return
	 */
	@PostMapping("/submit")
	public CalculateExchangeDto exchange(@Validated @RequestBody CalculateExchangeReqDto reqDto){
		return new CalculateExchangeDto();
	}
}
