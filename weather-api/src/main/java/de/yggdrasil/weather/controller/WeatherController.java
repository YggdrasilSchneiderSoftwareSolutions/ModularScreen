package de.yggdrasil.weather.controller;

import de.yggdrasil.weather.WeatherGateway;
import de.yggdrasil.weather.model.CurrentWeatherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private WeatherGateway weatherGateway;

    @GetMapping("/currentweather")
    public CurrentWeatherModel getCurrentWeather() {
        return weatherGateway.fetchWeather();
    }
}
