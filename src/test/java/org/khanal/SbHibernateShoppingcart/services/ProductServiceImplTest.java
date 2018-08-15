package org.khanal.SbHibernateShoppingcart.services;

import org.junit.Before;
import org.junit.Test;
import org.khanal.SbHibernateShoppingcart.domain.Product;
import org.khanal.SbHibernateShoppingcart.repositories.ProductRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    private ProductService productService;
    private List<Product> productList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);
        productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setCode("111111");
        product1.setCreatedOn(new Date());
        product1.setImage(new byte[0]);
        product1.setPrice(new BigDecimal(12987987.1111111));

        Product product2 = new Product();
        product1.setCode("22222");
        product1.setCreatedOn(new Date());
        product1.setImage(new byte[0]);
        product1.setPrice(new BigDecimal(12987987.4555555));
        productService.saveAll(Arrays.asList(product1, product2));


    }

    @Test
    public void listAll() {
        productService.listAll();
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testMockCreation() {
        assertNotNull(productRepository);
        assertNotNull(productService);
    }

    @Test
    public void saveOrUpdate() {
    }

    @Test
    public void findByProductCode() {
    }

    @Test
    public void deleteProductByCode() {
    }

    @Test
    public void saveAll() {
        Product product1 = new Product();
        product1.setCode("111111");
        product1.setCreatedOn(new Date());
        product1.setImage(new byte[0]);
        product1.setPrice(new BigDecimal(12987987.1111111));

        Product product2 = new Product();
        product1.setCode("22222");
        product1.setCreatedOn(new Date());
        product1.setImage(new byte[0]);
        product1.setPrice(new BigDecimal(12987987.4555555));
        productService.saveAll(Arrays.asList(product1, product2));
        verify(productRepository, times(1)).saveAll(Arrays.asList(product1, product2));
    }
}