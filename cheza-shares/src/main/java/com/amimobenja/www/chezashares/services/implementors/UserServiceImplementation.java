/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amimobenja.www.chezashares.services.implementors;

import java.util.Arrays;
import java.util.List;
import com.amimobenja.www.chezashares.entities.Subscriber;
import com.amimobenja.www.chezashares.entities.repositories.SubscriberRepository;
import com.amimobenja.www.chezashares.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author baamimo
 */
@Service(value = "userService")
public class UserServiceImplementation implements UserDetailsService {

    @Autowired
    private SubscriberRepository subsRepo;

    @Override
    public UserDetails loadUserByUsername(String msisdn) throws UsernameNotFoundException {
        Subscriber subscriber = subsRepo.findByMsisdn(msisdn);
        if (subscriber == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
//        int roleId = subscriber.getSmRoleId(); /** Use this role ID to later retrieve the role name. **/
        List authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new org.springframework.security.core.userdetails.User(subscriber.getMsisdn(), subscriber.getPassword(), authorities);
    }

}
