package de.yggdrasil.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Snow {
    @JsonProperty(value = "1h")
    private double volumeLastOneHour;

    @JsonProperty(value = "3h")
    private double volumeLastThreeHours;
}
