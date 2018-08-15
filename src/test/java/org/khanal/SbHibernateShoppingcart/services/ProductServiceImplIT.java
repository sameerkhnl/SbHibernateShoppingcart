package org.khanal.SbHibernateShoppingcart.services;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.khanal.SbHibernateShoppingcart.domain.Product;
import org.khanal.SbHibernateShoppingcart.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceImplIT {
    private ProductService productService;
    private ProductRepository productRepository;
    List<Product> productList;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Before
    public void setUp() throws Exception {
        Product product1 = new Product();
        product1.setCode("111111");
        product1.setCreatedOn(new Date());
        product1.setImage(new byte[0]);
        product1.setPrice(new BigDecimal(12987987.1111111));
        product1.setName("product1");
        //product1.set

        Product product2 = new Product();
        product2.setCode("22222");
        product2.setCreatedOn(new Date());
        product2.setImage(new byte[0]);
        product2.setPrice(new BigDecimal(12987987.4555555));
        product2.setName("product2");

        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
    }

    @Test
    public void a_saveAll() {
        assertEquals(2, productService.saveAll(productList).size());

    }

    @Test
    public void b_listAll() {
       assertEquals(7, productService.listAll().size());
    }

    @Test
    public void c_saveOrUpdate() {
        Product product3 = new Product();
        product3.setCode("333333");
        product3.setCreatedOn(new Date());
        product3.setImage(new byte[0]);
        product3.setPrice(new BigDecimal(12987987.333333));
        product3.setName("product3");
        productService.saveOrUpdate(product3);
        assertEquals(8, productService.listAll().size());
    }

    @Test
    public void d_findByProductCode() {
        assertEquals("Core Java", productService.findByProductCode("S001").getName());
    }

    @Test
    public void e_deleteProductByCode() {
        productService.deleteProductByCode("111111");
        assertEquals(7, productService.listAll().size());
    }


}