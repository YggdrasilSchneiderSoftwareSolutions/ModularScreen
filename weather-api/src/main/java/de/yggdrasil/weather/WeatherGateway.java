package de.yggdrasil.weather;

import de.yggdrasil.weather.model.CurrentWeatherModel;

public interface WeatherGateway {
    CurrentWeatherModel fetchWeather();
}
