package com.eurovision.demo.utils;

import com.eurovision.demo.domain.Cities;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CitiesUtilTest {

    List<Cities> cities = new ArrayList() {{
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
    int[] cityIdArr = new int [cities.size()];

    @BeforeAll
    void init(){

        AtomicInteger i= new AtomicInteger();
        cities.stream().forEach((c)->{
            cityIdArr[i.get()]= Math.toIntExact(c.getId());
            i.getAndIncrement();
        });
    }

    @Test
    void testLongestSequence(){

       assertTrue(("67 75 87 103 176 260 290 326".equals(CitiesUtil.getLongestSequence(cityIdArr)))
               ||("67 75 124 197 227 260 290 326".equals(CitiesUtil.getLongestSequence(cityIdArr))));
        int my_arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
        assertTrue("10 22 33 50 60".equals(CitiesUtil.getLongestSequence(my_arr)));

    }
}