package com.exchange.data.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExchangeInfo {
	private String from;
	private String to;
	private double qoutes;
}
