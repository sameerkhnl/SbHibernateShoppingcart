package org.khanal.SbHibernateShoppingcart.services;

import org.khanal.SbHibernateShoppingcart.domain.Product;
import org.khanal.SbHibernateShoppingcart.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product getById(Integer id) {
        return null;
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return productRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Product findByProductCode(String code) {
        return productRepository.findByCode(code).orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public void deleteProductByCode(String code) {
        productRepository.deleteProductByCode(code);
    }

    @Override
    public List<Product> saveAll(Collection<Product> products) {
        List<Product> productList = new ArrayList<>();
        productRepository.saveAll(products).forEach(productList::add);
        return productList;
    }


}
