##  Gold Medal Metrics - SpringBoot Project

### Overview

This is an Olympic gold medal metrics reporting web application called Gold Medal Metrics. Gold Medal Metrics allows users to:

* View countries in a list with their population, GDP, and number of Olympic gold medals. 
* Sort the list of countries by any of these attributes, as well as alphabetically by name. 
* View a detailed description of a country, with statistics on their Olympic wins. 
* View a list of every Olympic win a country has with the year, season, winner name, city, and event. 
* Sort the list of Olympic wins by any of these attributes.

### Requirements
* Java 11
* Gradle

### Technologies used
* Spring Boot framework
* Swagger API

### To start the application

To run the application, run the following command in a terminal window (in the complete) directory:
````
./gradlew bootRun
````

### With web application
Server:
``
localhost:8080/
``

### API Documentation

Link to Swagger Documentation

### Data Model

```markdown
    CountrySummaryResponse

    name // string
    code // string
    gdp // number
    population // stringSet
    medals // list
```

```markdown
    CountryDetailResponse
    name // string
    gdp //number
    population //integer
    numberMedals //integer
    numberSummerWins //integer
    percentageTotalSummerWins //number
    yearFirstSummerWin //integer
    numberWinterWins //integer
    percentageTotalWinterWins //number
    yearFirstWinterWin //integer
    numberEventsWonByFemaleAthletes //integer
    numberEventsWonByMaleAthletes //integer
    
```


