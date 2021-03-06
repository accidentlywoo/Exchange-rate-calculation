package com.exchange.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.AcceptLanguage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.LoggingCodecSupport;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Configuration
public class WebClientConfig {

	@Bean
	public WebClient webClient(){
		ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
					.codecs(conf -> conf.defaultCodecs().maxInMemorySize(1024*1024*50))
					// Spring WebFlux : codec처리를 위한 in memory buffer값이 256KB 기본설정 :: DataBufferLimitException 발생가능
					.build();

		exchangeStrategies.messageWriters().stream()
				.filter(LoggingCodecSupport.class::isInstance)
				.forEach(e -> ((LoggingCodecSupport) e).setEnableLoggingRequestDetails(true));
		return WebClient.builder()
					.clientConnector(
							new ReactorClientHttpConnector(
									HttpClient.create().secure()
									.tcpConfiguration(client ->
											client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 120_000)
											.doOnConnected(con ->
													con.addHandlerLast(new ReadTimeoutHandler(180))
													.addHandlerLast(new WriteTimeoutHandler(180))
											)
									)
							)
					)
					.exchangeStrategies(exchangeStrategies)
					.filter(ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
								log.debug("Request : {} {} ", clientRequest.method(), clientRequest.url());
								clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.debug("{} : {}", name, value)));
								return Mono.just(clientRequest);
							}
					))
					.filter(ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
								clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> log.debug("{} : {}", name, value)));
								return Mono.just(clientResponse);
							}
					))
					.defaultHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.3")
					.defaultHeader("accept-language", "Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.build();
	}
}
