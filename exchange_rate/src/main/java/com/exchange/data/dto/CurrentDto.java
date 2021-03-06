package com.exchange.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrentDto {
	private String success;
	private String terms;
	private String privacy;
	private boolean historical;
	private String date;
	private String timestamp;
	private String source;
	private Map<String, Double> quotes;
}
