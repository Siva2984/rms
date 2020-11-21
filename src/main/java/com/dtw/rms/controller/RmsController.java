package com.dtw.rms.controller;


import com.dtw.rms.exception.InternalServerException;
import com.dtw.rms.exception.RateNotFoundException;
import com.dtw.rms.model.Rate;
import com.dtw.rms.service.RmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The RmsController to provide the Services to return rate
 *
 * @author Sivakumar Panchu
 * @version 1.0
 * @since 20/11/2020
 */
@RestController
public class RmsController {
    public static final Logger logger = LoggerFactory.getLogger(RmsController.class);

    private final RmsService rmsService;

    @Autowired
    public RmsController(RmsService rmsService) {
        this.rmsService = rmsService;
    }


    /**
     * This method is used to get Rate.
     *
     * @param rateId search criteria from user
     * @return rate list
     */
    @GetMapping("/surcharge/{rateId}")
    @ResponseBody
    public Rate getRate(@PathVariable Long rateId) {

        try {
            logger.info("search requested with RateID by user: "+ rateId);
            Rate rate = rmsService.findByRateId(rateId).orElseGet(Rate::new);
            if(null != rate.getRateId()) {
                return rate;
            } else {
                throw new RateNotFoundException("Not Found","Rate ID not found in RMS");
            }
        } catch (Exception e) {
            logger.error("Error while fetching Rate", e);
            throw new RateNotFoundException("Not Found", "Rate ID not found in RMS");
        }
    }
    /**
     * This method is used to get All Rate.
     *
     * @return rate list
     */
    @GetMapping("/surcharge")
    @ResponseBody
    public List<Rate> getRates() {

        try {
            logger.info("Fetch all the rates: ");
            return rmsService.findAll();
        } catch (Exception e) {
            logger.error("Error while fetching Rates", e);
            return null;
        }
    }

    /**
     * This method is used for creating Rate.
     *
     * @param rate  from user
     * @return rate list
     */
    @PostMapping("/surcharge")
    @ResponseBody
    public Rate createRate(@RequestBody Rate rate) {

        try {
            logger.info("Create Rate : "+ rate.getRateId());
            return Optional.ofNullable(rate)
                    .map(rmsService::save)
                    .orElseGet(Rate::new)
                    ;
        } catch (Exception e) {
            logger.error("Error while Creating Rate ", e);
            throw new InternalServerException("Internal Servererror. Please contact admin");
        }
    }

    /**
     * This method is used for updating Rate.
     *
     * @param rate  from user
     * @return rate list
     */
    @PutMapping("/surcharge/{rateId}")
    @ResponseBody
    public Rate updateRate(@RequestBody Rate rate, @PathVariable Long rateId) {

        try {
            logger.info("Update rate : "+ rateId);

            return Optional.ofNullable(rateId)
                    .flatMap(rmsService::findByRateId)
                    .filter(rate1 -> Optional.ofNullable(rate1.getRateId()).isPresent())
                    .map(rate1 -> {
                        rate.setRateId(rateId);
                        return rmsService.save(rate);})
                    .orElseGet(Rate::new)
                    ;
        } catch (Exception e) {
            logger.error("Error while Updating Rate ", e);
            throw new InternalServerException("Internal Servererror. Please contact admin");
        }
    }

    /**
     * This method is used for updating Rate.
     *
     * @param rateId from user
     * @return rate list
     */
    @DeleteMapping("/surcharge/{rateId}")
    @ResponseBody
    public void deleteRate(@PathVariable Long rateId) {

        try {
            logger.info("Delete Rate : "+ rateId );
            rmsService.deleteById(rateId);
        } catch (Exception e) {
            logger.error("Error while Deleting Rate ", e);
            throw new InternalServerException("Internal Servererror. Please contact admin");
        }
    }

}