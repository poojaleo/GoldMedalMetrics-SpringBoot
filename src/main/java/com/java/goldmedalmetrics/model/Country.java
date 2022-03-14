package com.java.goldmedalmetrics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
@AllArgsConstructor
public class Country {
    @Id
    @GeneratedValue
    private Long ID;

    private String name;
    private String code;
    private BigDecimal gdp;
    private Integer population;

    public Country(String name, String code, BigDecimal gdp, Integer population) {
        this.name = name;
        this.code = code;
        this.gdp = gdp;
        this.population = population;
    }

    public Country(Country country) {
        this.name = country.getName();
        this.code = country.getCode();
        this.gdp = country.getGdp();
        this.population = country.getPopulation();
    }

    @Override
    public String toString() {
        return "Country{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", gdp=" + gdp +
                ", population=" + population +
                '}';
    }
}
