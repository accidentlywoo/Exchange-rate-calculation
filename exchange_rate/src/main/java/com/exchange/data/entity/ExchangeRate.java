package com.exchange.data.entity;

import com.exchange.data.dto.ExchangeInfo;

import javax.persistence.*;

@Entity
public class ExchangeRate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fromNation;

	private String toNation;

	private double qoutes;

	protected ExchangeRate(){}

	public ExchangeInfo getExchangeRateInfo(){
		return ExchangeInfo.builder()
				.from(fromNation)
				.to(toNation)
				.qoutes(qoutes)
				.build();
	}
}
