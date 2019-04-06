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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author afro
 */
@RestController
public class SubscriberController {
    
    private static final Logger logger = LogManager.getLogger(SubscriberController.class);

    @Autowired
    private SubscriberService subService;

    public SubscriberController(SubscriberService subService) {
        this.subService = subService;
    }

    @PostMapping(value = "/subscribe")
    private ResponseEntity<ResponseWrapper> subscribeSubscriber(@RequestBody Subscriber subscriber) {
        logger.info("REGISTRATION REQUEST RECEIVED.");
        Validation validate = new Validation();
        ResponseWrapper response = validate.validateSubscriberRegistration(subscriber);

        if (validate.isIsValid()) {
            try {
                EncryptPassword encrypt = new EncryptPassword();
                subscriber.setPassword(encrypt.encodePasswordMD5(subscriber.getPassword()));
                Subscriber savedSubscriber = subService.saveSubscriber(subscriber);
                response.setObject(savedSubscriber);
            } catch (NoSuchAlgorithmException ex) {
                logger.error(PASSWORD_ENCRYPTION_RESPONSE_ERROR);
                response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.setResponseMessage(PASSWORD_ENCRYPTION_RESPONSE_ERROR);                
            }
        }

        return new ResponseEntity<>(response, response.getHttpStatus());

    }

}
