package com.exchange.service;

import com.exchange.data.dto.CalculateExchangeDto;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.persistence.Entity;
import java.net.URI;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@ExtendWith({MockitoExtension.class})
public class WebClientTest {
	@Value("currency.accessKey")
	private String ACCESS_KEY="607762c676af5a7b9db6fb3a25ff5cad";

	@Value("currency.endpoint")
	private String ENDPOINT ="api.currencylayer.com";

	String url = ENDPOINT + "?" + "access_key=" + ACCESS_KEY;

	@Test
	public void WebClient(){
		HttpClient httpClient = HttpClient.create()
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
				.responseTimeout(Duration.ofMillis(5000))
				.doOnConnected(conn ->
						conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
								.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS))
				);


		WebClient client = WebClient.builder()
				.baseUrl("http://"+url)
				.defaultCookie("isLive","cookieTest")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultUriVariables(Collections.singletonMap("url","http://"+url))
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();

		WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
		WebClient.RequestBodySpec bodySpec = uriSpec.uri(URI.create(url));
	}

	@Test
	public void webclient_동작테스트() {
		// given
		WebClient webClient = WebClient.create();
		Mono<Void> hello = webClient
				.mutate()
				.baseUrl("http://" + url)
				.build()
				.get()
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Void.class);
//				.exchange()
//				.flatMap(res -> res.bodyToMono(CalculateExchangeDto.class));
		// when
		// than
		System.out.println("  result : " + hello);

		List<CalculateExchangeDto> collect = webClient
				.mutate()
				.baseUrl("http://" + ENDPOINT)
				.build()
				.get()
				.uri("/live?access_key=" + ACCESS_KEY)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(CalculateExchangeDto.class)
				.toStream()
				.collect(Collectors.toList());

		System.out.println("  result : "+collect);
	}
}
