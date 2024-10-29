package com.example.tobi.team3.service;

import com.example.tobi.team3.client.ExchangeRateClient;
import com.example.tobi.team3.dto.ExchangeDTO.KRWDTO;
import com.example.tobi.team3.dto.ExchangeDTO.USDKRW;
import com.example.tobi.team3.dto.ExchangeRateResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {
    private final ExchangeRateClient exchangeRateClient;
    private final ObjectMapper objectMapper;
    private Double latestKRWRate;

//    public String getExchangeRates(String accessKey) throws JsonProcessingException {
//        String exchange = exchangeRateClient.getExchangeRates(accessKey);
//        KRWDTO krwDto = objectMapper.readValue(exchange, KRWDTO.class);
//        Double KRW = Math.round(krwDto.getQuotes().get("USDKRW") * 10.0) / 10.0;
//        return objectMapper.writeValueAsString(KRW);
//    }
//
//    @PostConstruct
//    public void init() {
//        updateExchangeRate();  // 처음에 한 번 실행
//    }
//
//    @Scheduled(cron = "0 0 0/12 * * ?")
//    public void updateExchangeRate(){
//        try {
//            String accessKey = "8c8423b86162dfd63e92ad1b33138d70";
//            String exchangeRateString = getExchangeRates(accessKey);
//            System.out.println("받은 데이터: " + exchangeRateString);
//
//            latestKRWRate = Double.parseDouble(exchangeRateString);
//            System.out.println("12시간마다 업데이트된 환율: " + latestKRWRate);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }

        public Double getLatestKRWRate () {
            return 1383.9;
        }

    }

