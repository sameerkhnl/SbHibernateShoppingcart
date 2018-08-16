package org.khanal.SbHibernateShoppingcart.services;

import org.khanal.SbHibernateShoppingcart.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;


public interface ProductService extends CRUDService<Product> {
    Product findByProductCode(String code);
    void deleteProductByCode(String code);
    List<Product> saveAll(Collection<Product> products);
    Page<Product> findAll(int page, int size, Sort.Direction direction, String...properties);
}
