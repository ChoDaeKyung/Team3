package com.example.tobi.team3.client;
import com.example.tobi.team3.dto.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "exchangeRateClient", url = "http://api.currencylayer.com")
public interface ExchangeRateClient {

    @GetMapping("/live")
    String getExchangeRates(@RequestParam("access_key") String accessKey);
}