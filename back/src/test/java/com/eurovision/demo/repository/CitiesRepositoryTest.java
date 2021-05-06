package com.eurovision.demo.repository;

import com.eurovision.demo.domain.Cities;
import com.eurovision.demo.exceptions.ObjectNotFoundException;
import com.eurovision.demo.service.CitiesService;
import com.eurovision.demo.utils.Consts;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CitiesRepositoryTest {

    @Autowired
    CitiesRepository citiesRepository;

    private static final String CITY="Carabanchel";

    @Test
    @DisplayName("Save a city")
    @Order(0)
    void saveCity() {

        assertNotNull(createDummyCity().getId());

    }

    @Test
    @DisplayName("Find a city by name")
    @Order(1)
    void findCityByName() {

        createDummyCity();

        PageRequest pageRequest = PageRequest.of(
                Integer.parseInt(Consts.CONTROLLERPAGEDEFAULTVALUE),
                Integer.parseInt(Consts.CONTROLLERSIZEDEFAULTVALUE),
                Sort.Direction.fromString(Consts.CONTROLLERDIRECTIONDEFAULTVALUE),
                Consts.CONTROLLERORDERBYDEFAULTVALUE);
        Page<Cities> response = citiesRepository.findByNameContaining(CITY, pageRequest);
        assertEquals(1,response.getContent().size());
        assertEquals( 0,citiesRepository.findByNameContaining("Aluche", pageRequest).getTotalElements(),"Result should be empty");

    }

    private Cities createDummyCity(){
        Cities dummyCity= new Cities();
        dummyCity.setName(CITY);
        citiesRepository.save(dummyCity);

        return dummyCity;

    }





}