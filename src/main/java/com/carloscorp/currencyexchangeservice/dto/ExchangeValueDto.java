package com.carloscorp.currencyexchangeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeValueDto {

    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private String environment;
}
