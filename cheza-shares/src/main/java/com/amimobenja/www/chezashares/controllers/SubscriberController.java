/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amimobenja.www.chezashares.controllers;

import com.amimobenja.www.chezashares.entities.Subscriber;
import org.springframework.web.bind.annotation.RestController;
import com.amimobenja.www.chezashares.services.SubscriberService;
import static com.amimobenja.www.chezashares.utils.AppConstants.*;
import com.amimobenja.www.chezashares.utils.EncryptPassword;
import com.amimobenja.www.chezashares.utils.ResponseWrapper;
import com.amimobenja.www.chezashares.validator.Validation;
import java.security.NoSuchAlgorithmException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author afro
 */
@RestController
@RequestMapping("/subscribe")
public class SubscriberController {

    private static final Logger logger = LogManager.getLogger(SubscriberController.class);

    @Autowired
    private SubscriberService subService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SubscriberController(SubscriberService subService) {
        this.subService = subService;
    }

    @PostMapping(value = "/client")
    private ResponseEntity<ResponseWrapper> subscribeSubscriber(@RequestBody Subscriber subscriber) {
        logger.info("REGISTRATION REQUEST RECEIVED.");
        Validation validate = new Validation();
        ResponseWrapper response = validate.validateSubscriberRegistration(subscriber, subService);

        if (validate.isIsValid()) {
            subscriber.setPassword(bCryptPasswordEncoder.encode(subscriber.getPassword()));
            String msidn = validate.formatMsisdn(subscriber.getMsisdn());
            subscriber.setMsisdn(msidn);
            Subscriber savedSubscriber = subService.saveSubscriber(subscriber);
            response.setObject(savedSubscriber);
        }

        return new ResponseEntity<>(response, response.getHttpStatus());

    }

}
