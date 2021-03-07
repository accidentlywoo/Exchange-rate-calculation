package com.exchange.data.entity;

import com.exchange.data.ReceptionConstant;
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

	private boolean isCurrent;

	protected ExchangeRate(){}

	private ExchangeRate(String fromNation, double qoutes) {
		this.fromNation = fromNation;
		toNation = ReceptionConstant.REC_CODE.USA.getCode();
		this.qoutes = qoutes;
		isCurrent = true;
	}
	public static ExchangeRate createExchangeRate(String fromNation, double qoutes){
		return new ExchangeRate(fromNation,qoutes);
	}

	public ExchangeInfo getExchangeRateInfo(){
		return ExchangeInfo.builder()
				.from(fromNation)
				.to(toNation)
				.qoutes(qoutes)
				.build();
	}
	public void updateCurrentFalse(){
		isCurrent = false;
	}
	public double getQoutes(){return qoutes;}
}
