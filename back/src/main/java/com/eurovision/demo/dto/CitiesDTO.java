package com.eurovision.demo.dto;

import com.eurovision.demo.domain.Cities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitiesDTO {

    private long id;
    private String name;

    public CitiesDTO(CitiesDTOBuilder citiesDTOBuilder) {

        setId(citiesDTOBuilder.cities.getId());
        setName(citiesDTOBuilder.cities.getName());
    }


    public static CitiesDTOBuilder builder() {
        return new CitiesDTOBuilder();
    }


    public static final class CitiesDTOBuilder{

        private Cities cities;

        private CitiesDTOBuilder() {
        }

        public CitiesDTOBuilder withCities(Cities cities) {
            this.cities = cities;
            return this;
        }

        public CitiesDTO build() {
            return new CitiesDTO(this);
        }

    }
}
