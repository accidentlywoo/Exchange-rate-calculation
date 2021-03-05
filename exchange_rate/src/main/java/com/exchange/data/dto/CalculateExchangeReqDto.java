package com.exchange.data.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CalculateExchangeReqDto {

	@NotNull
	private String from;

	@NotNull
	private String to;

	@NotNull
	private double amount;

	@NotNull
	private String format;
}
