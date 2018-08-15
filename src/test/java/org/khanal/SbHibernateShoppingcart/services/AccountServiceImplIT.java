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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;
    private AccountRepository accountRepository;

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Before
    public void setUp() throws Exception {
        this.accountService = new AccountServiceImpl(accountRepository, passwordEncoder);
    }

    @Test
    public void findByUsername() {
        Account retrieved = accountService.findByUsername("sameerkhnl");
        assertNotNull(retrieved);
        assertEquals("pwd", retrieved.getEncryptedPassword());
    }

    @Test
    public void saveOrUpdate() {
        Account account = new Account();
        account.setPassword("pd");
        account.setRole("EMPLOYEE");
        account.setUsername("usertest1");
        //account.setEncryptedPassword(passwordEncoder.encode(account.getPassword()));
        account.setActive(false);
        Account retrieved = accountService.saveOrUpdate(account);
        System.out.println("##########" + retrieved.getEncryptedPassword() + "##############");
        assertEquals("usertest1", retrieved.getUsername());
    }
}