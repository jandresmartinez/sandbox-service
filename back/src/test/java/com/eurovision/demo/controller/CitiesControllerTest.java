package com.eurovision.demo.controller;

import com.eurovision.demo.domain.Cities;
import com.eurovision.demo.service.CitiesService;
import com.eurovision.demo.utils.Consts;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = {
        CitiesController.class
})
@Import(CitiesControllerTest.Config.class)
class CitiesControllerTest {

    @Autowired
    private CitiesService citiesService;

    @Autowired
    private CitiesController citiesController;

    private MockMvc mockMvc;

    private Cities dummyCity;

    private List<Cities> mockedCities ;

    @BeforeAll
    public void initMocks()  {
        mockMvc = MockMvcBuilders
                .standaloneSetup(citiesController)
                .build();

        dummyCity= new Cities(233L,"Abadan");

    }

    @Test
    void getSingleCity_shouldReturnNotFound() throws Exception {

      final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get("/cities/"+dummyCity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(MockMvcResultMatchers.status().isNotFound())

              .andDo(MockMvcResultHandlers.print());

        Mockito.verify(citiesService).findOne(dummyCity.getId());
    }



    @TestConfiguration
    protected static class Config {

        @Bean
        public CitiesService citiesService() {
            return Mockito.mock(CitiesService.class);
        }

    }

}