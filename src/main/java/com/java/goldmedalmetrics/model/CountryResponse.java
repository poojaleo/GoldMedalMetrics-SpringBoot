package com.java.goldmedalmetrics.model;

import lombok.Getter;

import java.util.List;

@Getter
public class CountryResponse {
    private List<CountrySummary> countries;

    public CountryResponse(List<CountrySummary> countries) {
        this.countries = countries;
    }
}
