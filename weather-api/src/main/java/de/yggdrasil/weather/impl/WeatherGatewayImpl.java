package de.yggdrasil.weather.impl;

import de.yggdrasil.weather.WeatherGateway;
import de.yggdrasil.weather.model.CurrentWeatherModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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

    @Value("${openweathermap.zip-code:zip-code-not-set}")
    private String zipCode;

    @Value("${openweathermap.country-code:country-code-not-set}")
    private String countryCode;

    @Value("${openweathermap.units:metric}")
    private String units;

    @Value("${openweathermap.lang:de}")
    private String lang;

    @Value("${openweathermap.uri:}")
    private String uri;

    @Autowired
    private WebClient webClient;

    @Autowired
    private Environment environment;

    @PostConstruct
    private void checkApiKey() {
        // Prüfung, ob API-Key über property-file gestzt wurde. Falls nicht, wird
        // Env var verwendet.
        if (openWeatherMapApiKey.isEmpty()) {
            log.info("API-Key for openweathermap was not set in property-file...trying to set it via environment variable");
            String apiKeyEnvironmentVar = System.getenv("api-key");
            openWeatherMapApiKey = apiKeyEnvironmentVar.isEmpty() ? "api-key-not-set"
                    : apiKeyEnvironmentVar;
        }
    }

    @Override
    public CurrentWeatherModel fetchWeather() {
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
