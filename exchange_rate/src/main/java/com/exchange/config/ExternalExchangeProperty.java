package com.exchange.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Component
@PropertySource(value = "classpath:external.properties")
public class ExternalExchangeProperty {
	@Value("${currency.accessKey}")
	private String accesskey;
	@Value("${currency.apiEndPoints}")
	private String apiendpoint;
}
