package com.java.goldmedalmetrics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class CountryMedalsListResponse {
    private List<GoldMedal> medals;
}
