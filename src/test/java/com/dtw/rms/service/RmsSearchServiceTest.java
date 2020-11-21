package com.dtw.rms.service;


import com.dtw.rms.model.Rate;

import com.dtw.rms.repository.RmsRepository;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RmsSearchServiceTest {
    @InjectMocks private RmsService rmsService;
    @Autowired
    @Mock private RmsRepository repository;
    private Rate test;
    private Map<String, String> requestParam;
    @BeforeEach
    void setUp() {

        test = new Rate();
        test.setRateDescription("Rate UnitTest");
        test.setRateEffectiveDate(Date.valueOf("2020-11-21"));
        test.setRateExpirationDate(Date.valueOf("2020-11-31"));
        test.setAmount(1000);

        rmsService = new RmsService(repository);

    }

    @Test
    void shouldCreateRate() {

        test = new Rate();
        test.setRateDescription("Rate UnitTest");
        test.setRateEffectiveDate(Date.valueOf("2020-11-21"));
        test.setRateExpirationDate(Date.valueOf("2020-11-31"));
        test.setAmount(1000);

        when(repository.save(test)).thenReturn(test);

        Rate response = rmsService.save(test);

        assertEquals(1000, response.getAmount());
        assertEquals("Rate UnitTest", response.getRateDescription());
        assertEquals(Date.valueOf("2020-11-21"), response.getRateEffectiveDate());
        assertEquals(Date.valueOf("2020-11-31"), response.getRateExpirationDate());

    }

    @Test
    void shouldGetRates() {
        List<Rate> rates = new ArrayList<>();
        rates.add(test);
        when(repository.findAll()).thenReturn(rates);

        rates = rmsService.findAll();

        assertEquals(1000, rates.get(0).getAmount());
        assertEquals("Rate UnitTest", rates.get(0).getRateDescription());
        assertEquals(Date.valueOf("2020-11-21"), rates.get(0).getRateEffectiveDate());
        assertEquals(Date.valueOf("2020-11-31"), rates.get(0).getRateExpirationDate());


    }

    @Test
    void shouldGetRateById() {
        test = new Rate();
        test.setRateDescription("Rate UnitTest");
        test.setRateEffectiveDate(Date.valueOf("2020-11-21"));
        test.setRateExpirationDate(Date.valueOf("2020-11-31"));
        test.setAmount(1000);

        when(repository.findById(any())).thenReturn(Optional.of(test));
        Rate response = rmsService.findByRateId(100L).get();

        assertEquals(1000, response.getAmount());
        assertEquals("Rate UnitTest", response.getRateDescription());
        assertEquals(Date.valueOf("2020-11-21"), response.getRateEffectiveDate());
        assertEquals(Date.valueOf("2020-11-31"), response.getRateExpirationDate());

    }
    @Test
    void shouldUpdateRate() {
        test = new Rate();
        test.setRateId(100L);
        test.setRateDescription("Rate Update UnitTest");
        test.setRateEffectiveDate(Date.valueOf("2020-11-21"));
        test.setRateExpirationDate(Date.valueOf("2020-11-31"));
        test.setAmount(10000);


        when(repository.save(test)).thenReturn(test);

        Rate response = rmsService.update(test);

        assertEquals(10000, response.getAmount());
        assertEquals("Rate Update UnitTest", response.getRateDescription());
        assertEquals(Date.valueOf("2020-11-21"), response.getRateEffectiveDate());
        assertEquals(Date.valueOf("2020-11-31"), response.getRateExpirationDate());

    }

    @Test
    void shouldDeleteRate() {


        rmsService.deleteById(100L);
    }



}