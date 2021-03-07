package com.exchange.repository;

import com.exchange.data.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchageRateRepository extends JpaRepository<ExchangeRate, Long> {
	Optional<ExchangeRate> findByFromNation(String from);
	Optional<ExchangeRate> findByFromNationAndIsCurrent(String from, boolean isCurrent);
}
