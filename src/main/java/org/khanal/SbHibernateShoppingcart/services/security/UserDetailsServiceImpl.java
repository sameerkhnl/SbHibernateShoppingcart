package org.khanal.SbHibernateShoppingcart.services.security;

import org.khanal.SbHibernateShoppingcart.converters.UserToUserDetailsConverter;
import org.khanal.SbHibernateShoppingcart.domain.Account;
import org.khanal.SbHibernateShoppingcart.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private AccountService accountService;
    private UserToUserDetailsConverter userToUserDetailsConverter;

    @Autowired
    public UserDetailsServiceImpl(AccountService accountService, UserToUserDetailsConverter userToUserDetailsConverter){
        this.accountService = accountService;
        this.userToUserDetailsConverter = userToUserDetailsConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByUsername(username);
        return userToUserDetailsConverter.convert(account);
    }
}
