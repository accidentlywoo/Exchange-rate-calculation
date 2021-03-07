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

	private ExchangeRate(String fromNation, String toNation, double qoutes) {
		this.fromNation = fromNation;
		this.toNation = toNation;
		this.qoutes = qoutes;
	}
	public static ExchangeRate createExchangeRate(String fromNation, String toNation, double qoutes){
		return new ExchangeRate(fromNation,toNation,qoutes);
	}

	public ExchangeInfo getExchangeRateInfo(){
		return ExchangeInfo.builder()
				.from(fromNation)
				.to(toNation)
				.qoutes(qoutes)
				.build();
	}
}
