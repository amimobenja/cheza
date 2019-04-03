/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amimobenja.www.chezashares.services;

import com.amimobenja.www.chezashares.entities.Subscriber;

/**
 *
 * @author afro
 */
public interface SubscriberService {
    
    public Subscriber searchSubscriberByMsisdnAndPassword(String msisdn, String password);
    
    public Subscriber searchSubscriberByMsisdnAndStatus(String msisdn, String status);
    
    public Subscriber saveSubscriber(Subscriber subscriber);
    
}
