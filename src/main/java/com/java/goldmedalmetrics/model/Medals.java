package com.java.goldmedalmetrics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Medals {
    private Iterable<GoldMedal> medals;

}
