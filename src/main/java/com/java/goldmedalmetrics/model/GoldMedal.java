package com.java.goldmedalmetrics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class GoldMedal {
    @Id
    private Long id;

    private Integer year;
    private String city;
    private String season;
    private String name;
    private String country;
    private String gender;
    private String sport;
    private String discipline;
    private String event;
}
