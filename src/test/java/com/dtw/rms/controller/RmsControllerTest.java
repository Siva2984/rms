package com.dtw.rms.controller;

import static org.junit.jupiter.api.Assertions.*;


import com.dtw.rms.model.Rate;
import com.dtw.rms.repository.RmsRepository;
import com.dtw.rms.service.RmsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RmsControllerTest {
    @Mock private RmsService rmsService;
    @InjectMocks private RmsController controller;
    @Mock private RmsRepository repository;
    private Rate test;

    @BeforeEach
    void setUp() {

        test = new Rate();
        test.setRateDescription("Rate UnitTest");
        test.setRateEffectiveDate(Date.valueOf("2020-11-21"));
        test.setRateExpirationDate(Date.valueOf("2020-11-31"));
        test.setAmount(1000);

        rmsService = new RmsService(repository);
        controller = new RmsController(rmsService);
    }


    @Test
    void shouldGetRates() {
        List<Rate> rates = new ArrayList<>();
        rates.add(test);
        when(rmsService.findAll()).thenReturn(rates);

        rates = controller.getRates();

        assertEquals(1000, rates.get(0).getAmount());
        assertEquals("Rate UnitTest", rates.get(0).getRateDescription());
        assertEquals(Date.valueOf("2020-11-21"), rates.get(0).getRateEffectiveDate());
        assertEquals(Date.valueOf("2020-11-31"), rates.get(0).getRateExpirationDate());


    }

    @Test
    void shouldUpdateRate() {
        test = new Rate();
        test.setRateId(100L);
        test.setRateDescription("Rate Update UnitTest");
        test.setRateEffectiveDate(Date.valueOf("2020-11-21"));
        test.setRateExpirationDate(Date.valueOf("2020-11-31"));
        test.setAmount(10000);

        when(rmsService.findByRateId(any())).thenReturn(Optional.of(test));
        when(rmsService.update(test)).thenReturn(test);

        Rate response = controller.updateRate(test, 100L);

        assertEquals(10000, response.getAmount());
        assertEquals("Rate Update UnitTest", response.getRateDescription());
        assertEquals(Date.valueOf("2020-11-21"), response.getRateEffectiveDate());
        assertEquals(Date.valueOf("2020-11-31"), response.getRateExpirationDate());

    }

    @Test
    void shouldDeleteRate() {
        controller.deleteRate(100L);
    }

    @Test
    void shouldCreateRate() {

        test = new Rate();
        test.setRateDescription("Rate UnitTest");
        test.setRateEffectiveDate(Date.valueOf("2020-11-21"));
        test.setRateExpirationDate(Date.valueOf("2020-11-31"));
        test.setAmount(1000);

        when(rmsService.save(test)).thenReturn(test);

        Rate response = controller.createRate(test);

        assertEquals(1000, response.getAmount());
        assertEquals("Rate UnitTest", response.getRateDescription());
        assertEquals(Date.valueOf("2020-11-21"), response.getRateEffectiveDate());
        assertEquals(Date.valueOf("2020-11-31"), response.getRateExpirationDate());

    }




}