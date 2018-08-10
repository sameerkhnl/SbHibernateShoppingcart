package org.khanal.SbHibernateShoppingcart.converters;

import org.khanal.SbHibernateShoppingcart.domain.Account;
import org.khanal.SbHibernateShoppingcart.services.security.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserToUserDetailsConverter implements Converter<Account, UserDetails> {

    @Override
    public UserDetails convert(Account account) {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        if(account != null){
            userDetails.setUsername(account.getUsername());
            userDetails.setPassword(account.getEncryptedPassword());
            userDetails.setEnabled(account.getActive());
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(account.getRole());
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(authority);
        }
        return userDetails;

    }
}
