package com.java.goldmedalmetrics.controller;

import com.java.goldmedalmetrics.model.*;
import com.java.goldmedalmetrics.repository.CountryRepository;
import com.java.goldmedalmetrics.repository.GoldMedalRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.text.WordUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/countries")
public class GoldMedalController {
    private final GoldMedalRepository goldMedalRepository;
    private final CountryRepository countryRepository;

    @GetMapping
    public CountryResponse getCountries(@RequestParam String sort_by, @RequestParam String ascending) {
        boolean ascendingOrder = ascending.equalsIgnoreCase("y");
        return new CountryResponse(getCountrySummary(sort_by.toLowerCase(), ascendingOrder));
    }

    @GetMapping("/{country}")
    public CountryDetailsResponse getCountryDetails(@PathVariable String country) {
        String countryName = WordUtils.capitalizeFully(country);
        return getCountryDetailsResponse(countryName);
    }

    @GetMapping("/{country}/medals")
    public CountryMedalsListResponse getCountryMedalList(@PathVariable String country,
                                                         @RequestParam String sort_by, @RequestParam String ascending) {
        String countryName = WordUtils.capitalizeFully(country);
        boolean ascendingOrder = ascending.equalsIgnoreCase("y");
        return getCountryMedalsListResponse(countryName, sort_by.toLowerCase(), ascendingOrder);
    }

    private CountryMedalsListResponse getCountryMedalsListResponse(String countryName, String sortBy, boolean ascOrder) {
        List<GoldMedal> medalList;
        switch (sortBy) {
            case "year":
                medalList = ascOrder ? goldMedalRepository.getByCountryOrderByYearAsc(countryName) :
                        goldMedalRepository.getByCountryOrderByYearDesc(countryName);
                break;
            case "season":
                medalList = ascOrder ? goldMedalRepository.getByCountryOrderBySeasonAsc(countryName) :
                        goldMedalRepository.getByCountryOrderBySeasonDesc(countryName);
                break;
            case "city":
                medalList = ascOrder ? goldMedalRepository.getByCountryOrderByCityAsc(countryName) :
                        goldMedalRepository.getByCountryOrderByCityDesc(countryName);
                break;
            case "name":
                medalList = ascOrder ? goldMedalRepository.getByCountryOrderByNameAsc(countryName) :
                        goldMedalRepository.getByCountryOrderByNameDesc(countryName);
                break;
            case "event":
                medalList = ascOrder ? goldMedalRepository.getByCountryOrderByEventAsc(countryName) :
                        goldMedalRepository.getByCountryOrderByEventDesc(countryName);
                break;
            default:
                medalList = new ArrayList<>();
                break;

        }

        return new CountryMedalsListResponse(medalList);
    }

    private CountryDetailsResponse getCountryDetailsResponse(String countryName) {
        Optional<Country> countryOptional = countryRepository.getByName(countryName);
        if(!countryOptional.isPresent()) {
            return new CountryDetailsResponse(countryName);
        }

        Country country = countryOptional.get();
        int goldMedalCount = goldMedalRepository.countByCountry(countryName);

        List<GoldMedal> summerWins = goldMedalRepository.getByCountryAndSeasonOrderByYearAsc(countryName, "Summer");
        int numberOfSummerWins = Math.max(summerWins.size(), 0);
        int totalSummerEvents = goldMedalRepository.countBySeason("Summer");

        float percentageTotalSummerWins = totalSummerEvents != 0 ? (float) numberOfSummerWins / totalSummerEvents : 0;
        Integer firstYearOfSummerWin = summerWins.size() > 0 ? summerWins.get(0).getYear() : null;

        List<GoldMedal> winterWins = goldMedalRepository.getByCountryAndSeasonOrderByYearAsc(countryName, "Winter");
        int numberOfWinterWins = Math.max(winterWins.size(), 0);

        int totalWinterEvents = goldMedalRepository.countBySeason("Winter");

        float percentageTotalWinterWins = totalSummerEvents != 0 ? (float) numberOfWinterWins / totalWinterEvents : 0;
        Integer firstYearOfWinterWin = winterWins.size() > 0 ? winterWins.get(0).getYear() : null;

        int numberOfEventsWonByFemaleAthletes = goldMedalRepository.countByCountryAndGender(countryName, "Women");
        int numberOfEventsWonByMaleAthletes = goldMedalRepository.countByCountryAndGender(countryName, "Men");

        return new CountryDetailsResponse(
                countryName, country.getGdp(), country.getPopulation(), goldMedalCount, numberOfSummerWins,
                percentageTotalSummerWins, firstYearOfSummerWin, numberOfWinterWins, percentageTotalWinterWins,
                firstYearOfWinterWin, numberOfEventsWonByFemaleAthletes, numberOfEventsWonByMaleAthletes);
    }

    private List<CountrySummary> getCountrySummary(String sortBy, boolean ascOrder) {
        List<Country> countries;
        switch (sortBy) {
            case "name":
                countries = ascOrder ? countryRepository.getAllByOrderByNameAsc() : countryRepository.getAllByOrderByNameDesc();
                break;
            case "gdp":
                countries = ascOrder ? countryRepository.getAllByOrderByGdpAsc() : countryRepository.getAllByOrderByGdpDesc();
                break;
            case "population":
                countries = ascOrder ? countryRepository.getAllByOrderByPopulationAsc() : countryRepository.getAllByOrderByPopulationDesc();
                break;
            case "medals":
            default:
                countries = countryRepository.getAllByOrderByNameAsc();
                break;
        }

        List<CountrySummary> countrySummaries = getCountrySummariesWithMedalCount(countries);

        if(sortBy.equalsIgnoreCase("medals")) {
            countrySummaries = sortByMedalCount(countrySummaries, ascOrder);
        }
        return countrySummaries;
    }

    private List<CountrySummary> sortByMedalCount(List<CountrySummary> countrySummaries, boolean ascOrder) {
        return countrySummaries.stream()
                .sorted((t1,t2) -> ascOrder ? t1.getMedals() - t2.getMedals() : t2.getMedals() - t1.getMedals())
                .collect(Collectors.toList());
    }

    private List<CountrySummary> getCountrySummariesWithMedalCount(List<Country> countries) {
        List<CountrySummary> countrySummaries = new ArrayList<>();
        for(Country country : countries) {
            int goldMedalCount = goldMedalRepository.countByCountry(country.getName());
            countrySummaries.add(new CountrySummary(country, goldMedalCount));
        }
        return countrySummaries;
    }
}
