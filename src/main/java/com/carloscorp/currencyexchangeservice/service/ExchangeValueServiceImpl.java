package com.carloscorp.currencyexchangeservice.service;

import com.carloscorp.currencyexchangeservice.datasource.domain.ExchangeValue;
import com.carloscorp.currencyexchangeservice.datasource.repository.ExchangeValueRepository;
import com.carloscorp.currencyexchangeservice.dto.ExchangeValueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeValueServiceImpl implements ExchangeValueService{

    private final ExchangeValueRepository exchangeValueRepository;
    private final Environment environment;

    @Override
    public ExchangeValueDto getExchangeValue(String from, String to){
        return exchangeValueRepository.findByCurrencyFromAndCurrencyTo(from, to)
                .map(this::buildDto)
                .orElse(null);

    }


    private ExchangeValueDto buildDto(ExchangeValue exchangeValue) {
        String port = Optional.ofNullable(environment.getProperty("local.server.port"))
                .orElse(null);

        return new ExchangeValueDto(
                exchangeValue.getCurrencyFrom(),
                exchangeValue.getCurrencyTo(),
                exchangeValue.getConversionMultiple(),
                port);
    }

}
