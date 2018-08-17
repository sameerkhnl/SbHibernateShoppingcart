package org.khanal.SbHibernateShoppingcart.repositories;

import org.khanal.SbHibernateShoppingcart.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByCode(String code);
    void deleteProductByCode(String code);
    Optional<Product> findProductByNameAndCode(String name, String code);

    Page<Product> findAll(Pageable pageable);
}
