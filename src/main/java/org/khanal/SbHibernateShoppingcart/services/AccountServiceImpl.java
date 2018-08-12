package org.khanal.SbHibernateShoppingcart.services;

import javassist.NotFoundException;
import org.khanal.SbHibernateShoppingcart.commands.CustomerInfoCommand;
import org.khanal.SbHibernateShoppingcart.domain.Account;
import org.khanal.SbHibernateShoppingcart.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findByUsername(String username) {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if(!accountOptional.isPresent()) {
            throw new RuntimeException("could not find customer info for user " + username);
        }

        return accountOptional.get();
    }

    @Override
    public void deleteAccountByUsername(String username) {
        accountRepository.deleteAccountByUsername(username);
    }

    @Override
    @Transactional
    public CustomerInfoCommand saveCustomerInfoCommand(CustomerInfoCommand customerInfoCommand) {
        return null;
    }

    @Override
    public List<Account> listAll() {
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }

    @Override
    public Account getById(Integer id) {
        return null;
    }

    @Override
    public Account saveOrUpdate(Account domainObject) {
        return accountRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {

    }
}
