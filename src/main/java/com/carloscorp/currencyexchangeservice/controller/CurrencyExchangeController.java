package com.carloscorp.currencyexchangeservice.controller;

import com.carloscorp.currencyexchangeservice.dto.ExchangeValueDto;
import com.carloscorp.currencyexchangeservice.service.ExchangeValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-exchange")
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final ExchangeValueService exchangeValueService;

    @GetMapping("/from/{from}/to/{to}")
    public ResponseEntity<ExchangeValueDto> getExchangeValue(
            @PathVariable String from,
            @PathVariable String to){

        return ResponseEntity.ok(exchangeValueService.getExchangeValue(from, to));
    }
}
