package org.khanal.SbHibernateShoppingcart.repositories;

import org.khanal.SbHibernateShoppingcart.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByCode(String code);
    void deleteProductByCode(String code);
}
