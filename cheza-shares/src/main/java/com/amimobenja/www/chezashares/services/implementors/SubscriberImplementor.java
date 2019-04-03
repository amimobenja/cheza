/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amimobenja.www.chezashares.services.implementors;

import com.amimobenja.www.chezashares.entities.Subscriber;
import com.amimobenja.www.chezashares.entities.repositories.SubscriberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.amimobenja.www.chezashares.services.SubscriberService;

/**
 *
 * @author afro
 */
@Transactional
@Service
public class SubscriberImplementor implements SubscriberService {
    
    private SubscriberRepository subRepo;

    public SubscriberImplementor(SubscriberRepository subRepo) {
        this.subRepo = subRepo;
    }
    
    @Override
    public Subscriber searchSubscriberByMsisdnAndPassword(String msisdn, String password) {
        return subRepo.findByMsisdnAndPassword(msisdn, password);
    }

    @Override
    public Subscriber saveSubscriber(Subscriber subscriber) {
        return subRepo.save(subscriber);
    }

    @Override
    public Subscriber searchSubscriberByMsisdnAndStatus(String msisdn, String status) {
        return subRepo.findByMsidnAndStatus(msisdn, status);
    }
    
}
