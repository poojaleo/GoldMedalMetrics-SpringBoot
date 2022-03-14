package com.java.goldmedalmetrics.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CountrySummary {
    private String name;
    private String code;
    private BigDecimal gdp;
    private Integer population;
    private int medals;

    public CountrySummary(Country country, int medals) {
        this.name = country.getName();
        this.code = country.getCode();
        this.gdp = country.getGdp();
        this.population = country.getPopulation();
        this.medals = medals;
    }
}
