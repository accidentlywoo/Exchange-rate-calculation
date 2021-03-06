package com.exchange.data.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalculateExchangeReqDto {

	@NotNull
	private String from;

	@NotNull
	private String to;

	@NotNull
	private double amount;
}
