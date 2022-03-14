package com.java.goldmedalmetrics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class CountryDetailsResponse {
    private String name;
    private BigDecimal gdp;
    private Integer population;
    private Integer numberMedals;
    private Integer numberSummerWins;
    private Float percentageTotalSummerWins;
    private Integer yearFirstSummerWin;
    private Integer numberWinterWins;
    private Float percentageTotalWinterWins;
    private Integer yearFirstWinterWin;
    private Integer numberEventsWonByFemaleAthletes;
    private Integer numberEventsWonByMaleAthletes;

    public CountryDetailsResponse(String name) {
        this.name = name;
    }


}
