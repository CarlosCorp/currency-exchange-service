package com.carloscorp.currencyexchangeservice.datasource.repository;

import com.carloscorp.currencyexchangeservice.datasource.domain.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

    Optional<ExchangeValue> findByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo);
}
