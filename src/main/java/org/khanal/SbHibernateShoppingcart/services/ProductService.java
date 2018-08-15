package org.khanal.SbHibernateShoppingcart.services;

import javafx.print.Collation;
import org.khanal.SbHibernateShoppingcart.domain.Product;
import org.khanal.SbHibernateShoppingcart.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


public interface ProductService extends CRUDService<Product> {
    Product findByProductCode(String code);
    void deleteProductByCode(String code);
    List<Product> saveAll(Collection<Product> products);
}
