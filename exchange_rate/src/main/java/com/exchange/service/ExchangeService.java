package com.exchange.service;

import com.exchange.data.ReceptionConstant;
import com.exchange.data.dto.CalculateExchangeDto;
import com.exchange.data.dto.CalculateExchangeReqDto;
import com.exchange.data.dto.CurrentDto;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.thymeleaf.util.DateUtils;
import reactor.netty.http.client.HttpClient;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExchangeService {
	private final RestTemplateBuilder restTemplateBuilder;
	// https://currencylayer.com/quickstart

	private final String ACCESS_KEY="607762c676af5a7b9db6fb3a25ff5cad";

	private final String ENDPOINT="api.currencylayer.com/";

	private final String FORMAT_JSON_QUERY_PARAM = "&format=1";

	public CurrentDto getCurrency(String currency) {
		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		WebClient webClient = WebClient.create();
		CurrentDto currentDto = webClient
				.mutate()
				.baseUrl("http://" + ENDPOINT)
				.build()
				.get()
				.uri("/historical?access_key=" + ACCESS_KEY+"&date="+now+"&currencies="+currency+FORMAT_JSON_QUERY_PARAM)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(CurrentDto.class)
				.toStream()
				.collect(Collectors.toList())
				.get(0);

		//http://api.currencylayer.com/historical?access_key=607762c676af5a7b9db6fb3a25ff5cad&date=2010-03-05&currencies=USD&format=1
		return currentDto;
	}

	public CalculateExchangeDto convert(CalculateExchangeReqDto reqDto) {

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
