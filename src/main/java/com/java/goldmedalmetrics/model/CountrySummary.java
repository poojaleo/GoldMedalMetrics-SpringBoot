package com.java.goldmedalmetrics.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CountrySummary extends Country {
    private int medals;

    public CountrySummary(Country country, int medals) {
        super(country);
        this.medals = medals;
    }
}
