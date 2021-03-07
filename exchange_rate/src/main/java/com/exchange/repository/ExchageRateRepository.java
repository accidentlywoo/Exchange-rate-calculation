package com.exchange.repository;

import com.exchange.data.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchageRateRepository extends JpaRepository<ExchangeRate, Long> {
}
