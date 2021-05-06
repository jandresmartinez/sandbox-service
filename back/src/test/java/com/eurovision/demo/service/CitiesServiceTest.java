package com.eurovision.demo.service;

import com.eurovision.demo.domain.Cities;
import com.eurovision.demo.exceptions.ObjectNotFoundException;
import com.eurovision.demo.repository.CitiesRepository;
import com.eurovision.demo.utils.Consts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CitiesServiceTest {

    @Test
    @DisplayName("Return page(list) of cities")
    void getCities_null_shouldReturnList() {
        // Given
        final CitiesRepository mockedCitiesRepository = Mockito.mock(CitiesRepository.class);
        final List<Cities> mockedCities =  new ArrayList() {
            {
                add(new Cities(233L, "Abadan"));
                add(new Cities(67L, "Abidjan"));
                add(new Cities(157L, "Accra"));
            }};

        PageRequest pageRequest = PageRequest.of(
                Integer.parseInt(Consts.CONTROLLERPAGEDEFAULTVALUE),
                Integer.parseInt(Consts.CONTROLLERSIZEDEFAULTVALUE),
                Sort.Direction.fromString(Consts.CONTROLLERDIRECTIONDEFAULTVALUE),
                Consts.CONTROLLERORDERBYDEFAULTVALUE);
        Page<Cities> page = new PageImpl<>(mockedCities);
        Mockito.when(
                mockedCitiesRepository.findAll(pageRequest)
                       ).thenReturn(page);



        final CitiesService citiesService = new CitiesService(mockedCitiesRepository);

        Page<Cities> results = citiesService.findPaginated(Integer.parseInt(Consts.CONTROLLERPAGEDEFAULTVALUE),
                Integer.parseInt(Consts.CONTROLLERSIZEDEFAULTVALUE),
                Consts.CONTROLLERDIRECTIONDEFAULTVALUE,
                Consts.CONTROLLERORDERBYDEFAULTVALUE);


        assertTrue(results.getContent().stream().anyMatch(e->e instanceof Cities));
    }


    @Test
    @DisplayName("Throw object not found exceptions when non existing id")
    void getCities_nonExistingId_shouldThrowException() {

        final CitiesRepository mockedCitiesRepository = Mockito.mock(CitiesRepository.class);
        Mockito.when(
                mockedCitiesRepository.findById(88L)
        ).thenReturn(null);
        final CitiesService citiesService = new CitiesService(mockedCitiesRepository);
        assertThrows(ObjectNotFoundException.class,() ->{ citiesService.findById(87L);});
    }



}