package de.yggdrasil.weather.impl;

import de.yggdrasil.weather.WeatherGateway;
import de.yggdrasil.weather.model.CurrentWeatherModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class WeatherGatewayImpl implements WeatherGateway {

    @Value("${openweathermap.api-key:}")
    private String openWeatherMapApiKey;

    @Value("${openweathermap.uri:}")
    private String uri;

    private String zipCode;
    private String countryCode;
    private String units;
    private String lang;

    @Autowired
    private WebClient webClient;

    @PostConstruct
    private void checkApiKey() {
        // Prüfung, ob API-Key und zip-code über property-file gestzt wurde.
        // Falls nicht, wird Env var verwendet.
        if (openWeatherMapApiKey.isEmpty()) {
            log.info("API-Key for openweathermap was not set in property-file...trying to set it via environment variable");
            String apiKeyEnvironmentVar = System.getenv("api-key");
            openWeatherMapApiKey = apiKeyEnvironmentVar.isEmpty() ? "api-key-not-set"
                    : apiKeyEnvironmentVar;
        }
    }

    @Override
    public CurrentWeatherModel fetchWeather(String zipCode, String countryCode, String units, String lang) {
        this.zipCode = zipCode;
        this.countryCode = countryCode;
        this.units = units;
        this.lang = lang;

        Mono<CurrentWeatherModel> weatherApiRequest = buildWeatherApiRequest();

        return weatherApiRequest.block();
    }

    private Mono<CurrentWeatherModel> buildWeatherApiRequest() {
        return webClient
                .get()
                .uri(uri, zipCode, countryCode, openWeatherMapApiKey, units, lang)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CurrentWeatherModel.class);
    }
}
