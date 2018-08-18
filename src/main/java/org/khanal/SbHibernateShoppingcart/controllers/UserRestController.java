package org.khanal.SbHibernateShoppingcart.controllers;


import org.khanal.SbHibernateShoppingcart.services.security.UserDetailsImpl;
import org.khanal.SbHibernateShoppingcart.services.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public UserDetails getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserDetails user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
        return user;
    }


    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public UserDetails getAuthenticatedUserr(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserDetails user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
        return user;
    }

}
