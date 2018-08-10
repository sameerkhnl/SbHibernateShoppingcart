package org.khanal.SbHibernateShoppingcart.services;

import org.khanal.SbHibernateShoppingcart.domain.Account;

import java.util.Optional;

public interface AccountService extends CRUDService<Account> {
    Optional<Account> findByUsername(String username);
    void deleteAccountByUsername(String username);
}
