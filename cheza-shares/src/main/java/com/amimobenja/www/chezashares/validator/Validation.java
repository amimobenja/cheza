/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amimobenja.www.chezashares.validator;

import com.amimobenja.www.chezashares.entities.Subscriber;
import com.amimobenja.www.chezashares.services.SubscriberService;
import static com.amimobenja.www.chezashares.utils.AppConstants.*;
import com.amimobenja.www.chezashares.utils.ResponseWrapper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
/**
 *
 * @author afro
 */

public class Validation {
    
    private static final Logger logger = LogManager.getLogger(Validation.class);
    private boolean isValid;
    
    public ResponseWrapper validateSubscriberRegistration(Subscriber subscriber, SubscriberService subService) {
        ResponseWrapper response = new ResponseWrapper();
        
        logger.debug(" - Validation process started.");
        
        String msidn = formatMsisdn(subscriber.getMsisdn());
        if (msidn.equals("")) {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setResponseMessage(BAD_PARAMS_RESPONSE_MESSAGE);
            response.setResponseCode(HttpStatus.BAD_REQUEST.value());

            return response;
        }
        
        Subscriber existingSubscriber = subService.loadSubscriberByMsisdn(msidn);
        
        if (existingSubscriber != null) {
            response.setHttpStatus(HttpStatus.CONFLICT);
            response.setResponseMessage(CONFLICT_RESPONSE_MESSAGE);
            response.setResponseCode(HttpStatus.CONFLICT.value());

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
    
    
    private boolean isValidInput(String inputStr, String expression) {
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }
    
    public String formatMsisdn(String msisdn) {
        String formatedMsisdn = "";
        if (isValidInput(msisdn, REGEX_EXPRESSION_I)) {
            formatedMsisdn = msisdn;
        } else if (isValidInput(msisdn, REGEX_EXPRESSION_II)) {
            formatedMsisdn = msisdn.replace("+", "");
        } else if (isValidInput(msisdn, REGEX_EXPRESSION_III)) {
            formatedMsisdn = String.valueOf(msisdn.charAt(0)).equals("0") ? msisdn.replaceFirst("0", "254") : msisdn;
        } else if (isValidInput(msisdn, REGEX_EXPRESSION_IV)) {
            formatedMsisdn = "254" + msisdn;
        }
        return formatedMsisdn;
    }

}
