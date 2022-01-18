package de.yggdrasil.weather.controller;

import de.yggdrasil.weather.WeatherGateway;
import de.yggdrasil.weather.model.CurrentWeatherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private WeatherGateway weatherGateway;

    @GetMapping("/currentweather")
    public CurrentWeatherModel getCurrentWeather(
            @RequestParam(value = "zipcode", required = true) String zipCode,
            @RequestParam(value = "countrycode", required = true) String countryCode,
            @RequestParam(value = "units", defaultValue = "metric") String units,
            @RequestParam(value = "lang", defaultValue = "de") String lang) {
        return weatherGateway.fetchWeather(zipCode, countryCode, units, lang);
    }
}
