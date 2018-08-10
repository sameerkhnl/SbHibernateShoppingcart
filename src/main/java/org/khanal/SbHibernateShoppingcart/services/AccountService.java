package org.khanal.SbHibernateShoppingcart.services;

import org.khanal.SbHibernateShoppingcart.domain.Account;

public interface AccountService extends CRUDService<Account> {
    Account findByUsername(String username);
    void deleteAccountByUsername(String username);
}
