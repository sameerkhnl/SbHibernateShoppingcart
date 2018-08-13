package org.khanal.SbHibernateShoppingcart.commands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductInfoCommandTest {
    ProductInfoCommand productInfoCommand;

    @Before
    public void setup() {
        productInfoCommand = new ProductInfoCommand("abc", "product1", 5.55);
    }

    @Test
    public void equals() {
        assertEquals(productInfoCommand, new ProductInfoCommand("abc", "product1", 5.55));
    }
}