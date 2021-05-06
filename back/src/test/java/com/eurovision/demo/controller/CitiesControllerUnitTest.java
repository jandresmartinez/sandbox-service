package com.eurovision.demo.controller;

import com.eurovision.demo.domain.Cities;
import com.eurovision.demo.dto.CitiesDTO;
import com.eurovision.demo.response.JsonResponseCreator;
import com.eurovision.demo.service.CitiesService;
import com.eurovision.demo.utils.Consts;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CitiesControllerUnitTest {

    private CitiesController citiesController;

    @Mock
    private CitiesService citiesService;

    @BeforeAll
    void init(){
        citiesService = Mockito.mock(CitiesService.class);
        citiesController= new CitiesController(citiesService);

    }
    @Test
    void getCities_null_shouldReturnListOfCities()  {

        final List<Cities> mockedCities =  new ArrayList() {
            {
                add(new Cities(233L, "Abadan"));
                add(new Cities(67L, "Abidjan"));
                add(new Cities(157L, "Accra"));
            }};

        Page<Cities> page = new PageImpl<>(mockedCities);

        Mockito.when(
                citiesService.findPaginated(
                        Integer.parseInt(Consts.CONTROLLERPAGEDEFAULTVALUE),
                        Integer.parseInt(Consts.CONTROLLERSIZEDEFAULTVALUE),
                        Consts.CONTROLLERDIRECTIONDEFAULTVALUE,
                        Consts.CONTROLLERORDERBYDEFAULTVALUE)).thenReturn(page);

        HttpEntity<JsonResponseCreator<CitiesDTO>> response = citiesController.getAllCities(null,Integer.parseInt(Consts.CONTROLLERPAGEDEFAULTVALUE), Integer.parseInt(Consts.CONTROLLERSIZEDEFAULTVALUE),Consts.CONTROLLERDIRECTIONDEFAULTVALUE);
        assertEquals(response.getBody().getContent().size(),mockedCities.size(),"Size of the cities do not match");
        assertEquals(response.getBody().getContent().get(0),CitiesDTO.builder().withCities(mockedCities.get(0)).build(),"Abadan city must be the same");

    }


}