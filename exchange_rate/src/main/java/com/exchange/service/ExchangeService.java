package com.exchange.service;

import com.exchange.config.ExternalExchangeProperty;
import com.exchange.data.ReceptionConstant;
import com.exchange.data.dto.CalculateExchangeDto;
import com.exchange.data.dto.CalculateExchangeReqDto;
import com.exchange.data.dto.CurrentDto;
import com.exchange.data.dto.ExchangeDto;
import com.exchange.data.entity.ExchangeRate;
import com.exchange.repository.ExchageRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExchangeService {

	private final ExchageRateRepository exchageRateRepository;


	public ResponseEntity<ExchangeDto> getCurrency(String currency) {
		Optional<ExchangeRate> byFromNationAndIsCurrent = exchageRateRepository.findByFromNationAndIsCurrent(currency, true);

		return ResponseEntity.ok(ExchangeDto.builder()
				.quotes(byFromNationAndIsCurrent.get().getQoutes())
				.build());
	}

	public ResponseEntity<CalculateExchangeDto> convert(CalculateExchangeReqDto reqDto) {

//		return ResponseEntity.ok(currentDto);
		return null;
	}

	public Map<String, String> getReception() {
		Map<String, String> recMap = new HashMap<>();
		recMap.put(ReceptionConstant.REC_CODE.KROREA.getNation(),ReceptionConstant.REC_CODE.KROREA.getCode());
		recMap.put(ReceptionConstant.REC_CODE.JAPAN.getNation(),ReceptionConstant.REC_CODE.JAPAN.getCode());
		recMap.put(ReceptionConstant.REC_CODE.PILIPINE.getNation(), ReceptionConstant.REC_CODE.PILIPINE.getCode());
		return recMap;
	}
}
