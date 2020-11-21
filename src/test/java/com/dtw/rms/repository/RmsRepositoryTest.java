package com.dtw.rms.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import com.dtw.rms.model.Rate;

import org.junit.jupiter.api.Test;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RmsRepositoryTest {
    @Autowired
    private RmsRepository repository;
    private Rate test;
    @BeforeEach
    public void before() {

        test = new Rate();
        test = new Rate();
        test.setRateDescription("Rate UnitTest");
        test.setRateEffectiveDate(Date.valueOf("2020-11-21"));
        test.setRateExpirationDate(Date.valueOf("2020-11-31"));
        test.setAmount(1000);

        repository.save(test);

    }

    @Test
    void shouldSaveRates() {
        test = repository.save(test);

        assertEquals(1000, test.getAmount());
        assertEquals("Rate UnitTest", test.getRateDescription());
        assertEquals(Date.valueOf("2020-11-21"), test.getRateEffectiveDate());
        assertEquals(Date.valueOf("2020-11-31"), test.getRateExpirationDate());


    }



}