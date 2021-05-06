package com.eurovision.demo.controller;

import com.eurovision.demo.domain.Cities;
import com.eurovision.demo.dto.CitiesDTO;
import com.eurovision.demo.response.JsonResponseCreator;
import com.eurovision.demo.service.CitiesService;
import com.eurovision.demo.utils.Consts;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MimeTypeUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@ExtendWith(SpringExtension.class)
@WebMvcTest(
        value = CitiesController.class
)

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CitiesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CitiesService citiesService;

    private Cities dummyCity;

    private List<Cities> mockedCities ;

    @BeforeAll
    public void initMocks()  {
        dummyCity= new Cities(233L,"Abadan");
        mockedCities =  new ArrayList() {
            {
                add(new Cities(233L,"Abadan"));
                add(new Cities(67L,"Abidjan"));
                add(new Cities(157L,"Accra"));
                add(new Cities(262L,"Ad-Dammam"));
                add(new Cities(255L,"Adana"));
                add(new Cities(75L,"Addis Ababa"));
                add(new Cities(297L,"Adelaide"));
                add(new Cities(124L,"Agadir"));
                add(new Cities(197L,"Agra"));
                add(new Cities(43L,"Ahmadabad"));
                add(new Cities(317L,"Ahvaz"));
                add(new Cities(87L,"Al-Jizah"));
                add(new Cities(147L,"Al-Khartum Bahri"));
                add(new Cities(227L,"Al-Madinah"));
                add(new Cities(103L,"Aleppo"));
                add(new Cities(47L,"Alexandria"));
                add(new Cities(176L,"Algiers"));
                add(new Cities(307L,"Allahabad"));
                add(new Cities(302L,"Almaty"));
                add(new Cities(260L,"Amman"));
                add(new Cities(290L,"Amritsar"));
                add(new Cities(50L,"Ankara"));
                add(new Cities(230L,"Anshan"));
                add(new Cities(160L,"Antananarivo"));
                add(new Cities(326L,"Aurangabad"));
                add(new Cities(27L,"Baghdad"));
                add(new Cities(130L,"Baku"));
                add(new Cities(234L,"Bamako"));
                add(new Cities(108L,"Bandung"));
                add(new Cities(29L,"Bangalore"));

            }};
    }

    @Test
    @DisplayName("Test Single City")
    void getSingleCity_shouldReturnOk() throws Exception {

        Mockito.when(citiesService.findOne(dummyCity.getId())).thenReturn(dummyCity);
        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get("/cities/"+dummyCity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(dummyCity.getId()))
              .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(dummyCity.getName()))
              .andDo(MockMvcResultHandlers.print());

        Mockito.verify(citiesService).findOne(dummyCity.getId());
    }

    @Test
    @DisplayName("Test List of City with pagination")
    void getQueryCity_shouldReturnOk() throws Exception {
        Page<Cities> page = new PageImpl<>(mockedCities);
        String size="5";

        Mockito.when(citiesService.findPaginated(Integer.parseInt(Consts.CONTROLLERPAGEDEFAULTVALUE),
                Integer.parseInt(size),
                Consts.CONTROLLERDIRECTIONDEFAULTVALUE,
                Consts.CONTROLLERORDERBYDEFAULTVALUE)).thenReturn(page);

        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get("/cities/queryByPage?page=0&size="+size)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").value(mockedCities.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pageSize").value(size))
                .andDo(MockMvcResultHandlers.print());

    }

}