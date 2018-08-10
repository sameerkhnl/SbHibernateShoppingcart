package org.khanal.SbHibernateShoppingcart.repositories;

import org.khanal.SbHibernateShoppingcart.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
    void deleteAccountByUsername(String username);
}
