package org.khanal.SbHibernateShoppingcart.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.khanal.SbHibernateShoppingcart.domain.Account;
import org.khanal.SbHibernateShoppingcart.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@SpringBootTest()
public class AccountServiceImplIT {
    private AccountService accountService;

    private AccountRepository accountRepository;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Before
    public void setUp() throws Exception {
        this.accountService = new AccountServiceImpl(accountRepository);
    }

    @Test
    public void findByUsername() {
        Account retrieved = accountService.findByUsername("sameerkhnl");
        assertNotNull(retrieved);
        assertEquals("pwd", retrieved.getEncryptedPassword());
    }
}