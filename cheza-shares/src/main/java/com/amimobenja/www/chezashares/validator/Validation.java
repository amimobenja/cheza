/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amimobenja.www.chezashares.validator;

import com.amimobenja.www.chezashares.entities.Subscriber;
import static com.amimobenja.www.chezashares.utils.AppConstants.*;
import com.amimobenja.www.chezashares.utils.ResponseWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

/**
 *
 * @author afro
 */
public class Validation {
    
    private static final Logger logger = LogManager.getLogger(Validation.class);

    private boolean isValid;

    public ResponseWrapper validateSubscriberRegistration(Subscriber subscriber) {
        ResponseWrapper response = new ResponseWrapper();
        
        logger.debug(" - Validation process started.");

        if (subscriber.getMsisdn().isEmpty()) {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setResponseMessage(BAD_PARAMS_RESPONSE_MESSAGE);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());

            return response;
        }

        if (subscriber.getFirstName().isEmpty()) {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setResponseMessage(BAD_PARAMS_RESPONSE_MESSAGE);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());

            return response;
        }

        if (subscriber.getIdNo().isEmpty()) {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setResponseMessage(BAD_PARAMS_RESPONSE_MESSAGE);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());

            return response;
        }

        if (subscriber.getPassword().isEmpty()) {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setResponseMessage(BAD_PARAMS_RESPONSE_MESSAGE);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());

            return response;
        }

        if (subscriber.getSecondName().isEmpty()) {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setResponseMessage(BAD_PARAMS_RESPONSE_MESSAGE);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());

            return response;
        }
        
        logger.debug(" - Entity Object is valid.");
        setIsValid(true);
        response.setHttpStatus(HttpStatus.CREATED);
        response.setResponseMessage(OBJECT_CREATED_RESPONSE_MESSAGE);
        response.setResponseCode(HttpStatus.CREATED.value());

        return response;
    }

    public boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

}
