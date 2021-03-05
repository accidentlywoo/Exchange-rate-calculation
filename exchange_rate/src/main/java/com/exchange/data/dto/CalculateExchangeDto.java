package com.exchange.data.dto;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalculateExchangeDto {
	private String success;
	private String terms;
	private String privacy;
	private String timestamp;
	private String source;
	private Map<String, Double> quotes;
}
