package com.dtw.rms.service;


import com.dtw.rms.model.Rate;
import com.dtw.rms.repository.RmsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

/**
 * Service Implementation
 *
 * @author Sivakumar Panchu
 * @version 1.0
 * @since 20/11/2020
 */
@Service
public class RmsService {
    public static final Logger logger = LoggerFactory.getLogger(RmsService.class);
    private RmsRepository rmsRepository;

    @Autowired
    public RmsService(RmsRepository rmsRepository) {
        this.rmsRepository = rmsRepository;

    }

    public Optional<Rate> findByRateId(Long rateId) {
        return rmsRepository.findById(rateId);
    }

    public List<Rate> findAll() {
        return rmsRepository.findAll();
    }

    public Rate save(Rate rate) {
        return rmsRepository.save(rate);
    }

    public Rate update(Rate rate) {
        return rmsRepository.save(rate);
    }

    public void deleteById(Long rateId) {
        rmsRepository.deleteById(rateId);
    }

}
