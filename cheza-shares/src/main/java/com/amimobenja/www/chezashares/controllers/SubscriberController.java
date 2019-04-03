/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amimobenja.www.chezashares.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.amimobenja.www.chezashares.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author afro
 */

@RestController
public class SubscriberController {
    
    @Autowired
    private SubscriberService subService;

    public SubscriberController(SubscriberService subService) {
        this.subService = subService;
    }
    
    @PostMapping(value="/subscribe")
    
    
    
    
}
