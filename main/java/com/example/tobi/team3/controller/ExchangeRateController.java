package com.example.tobi.team3.controller;

import com.example.tobi.team3.dto.ExchangeRateResponse;
import com.example.tobi.team3.service.ExchangeRateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping({"/exchange-rates"})
    public Double getExchangeRates(){
        System.out.println(exchangeRateService.getLatestKRWRate());
        return exchangeRateService.getLatestKRWRate();
    }

}
