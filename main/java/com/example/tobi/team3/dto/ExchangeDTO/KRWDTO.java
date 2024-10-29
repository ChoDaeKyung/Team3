package com.example.tobi.team3.dto.ExchangeDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class KRWDTO {
    private Map<String, Double> quotes;
}
