package org.khanal.SbHibernateShoppingcart.services;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.khanal.SbHibernateShoppingcart.commands.CustomerInfoCommand;
import org.khanal.SbHibernateShoppingcart.domain.Account;
import org.khanal.SbHibernateShoppingcart.repositories.AccountRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Ignore
public class AccountServiceImplTest {
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        accountService = new AccountServiceImpl(accountRepository, passwordEncoder);
    }


    @Test
    public void testMockCreation() {
        assertNotNull(accountRepository);
        assertNotNull(accountService);
    }

    @Test
    public void listAll() {
        Account account = new Account();
        account.setUsername("sameerkhnl");
        account.setActive(true);
        account.setPassword("pwd");
        account.setRole("ROLE_ADMIN");
        List<Account> accountList = new ArrayList<>();
        accountList.add(account);
        CustomerInfoCommand customerInfoCommand = new CustomerInfoCommand("customer1", "address1", "email1@example.com", "1111111", true);
        accountService.saveCustomerInfoCommand(customerInfoCommand);
        List<Account> retrievedList = accountService.listAll();


        assertEquals(accountList.size(), 1);
        verify(accountRepository, times(1)).findAll();

    }
}