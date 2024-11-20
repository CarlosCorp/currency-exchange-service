package com.carloscorp.currencyexchangeservice.service;

import com.carloscorp.currencyexchangeservice.dto.ExchangeValueDto;

public interface ExchangeValueService {
    ExchangeValueDto getExchangeValue(String from, String to);
}
