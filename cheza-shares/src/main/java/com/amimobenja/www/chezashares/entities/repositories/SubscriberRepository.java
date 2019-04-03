/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amimobenja.www.chezashares.entities.repositories;

import com.amimobenja.www.chezashares.entities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author afro
 */
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {
    
    public Subscriber findByMsisdnAndPassword(String msisdn, String password);
    
    public Subscriber findByMsidnAndStatus(String msidn, String status);
    
}
