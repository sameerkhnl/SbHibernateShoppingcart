package org.khanal.SbHibernateShoppingcart.services;

import javassist.NotFoundException;
import org.khanal.SbHibernateShoppingcart.commands.CustomerInfoCommand;
import org.khanal.SbHibernateShoppingcart.domain.Account;

import java.util.Optional;

public interface AccountService extends CRUDService<Account> {
    Account findByUsername(String username);
    void deleteAccountByUsername(String username);
    CustomerInfoCommand saveCustomerInfoCommand(CustomerInfoCommand customerInfoCommand);
}
