package org.khanal.SbHibernateShoppingcart.commands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CartLineInfoCommandTest {
    private ProductInfoCommand productInfoCommand;
    private CartLineInfoCommand cartLineInfoCommand;

    @Before
    public void setup(){
        productInfoCommand = new ProductInfoCommand("abc", "product1", 5.55);
        cartLineInfoCommand = new CartLineInfoCommand(productInfoCommand, 1);
    }

    @Test
    public void getProductInfoCommand() {
        assertEquals(cartLineInfoCommand.getProductInfoCommand(), productInfoCommand);
    }

    @Test
    public void setProductInfoCommand() {
    }

    @Test
    public void getQuantity() {
    }

    @Test
    public void setQuantity() {
    }

    @Test
    public void getAmount() {
    }

    @Test
    public void incrementQuantityByOne() {
        for(int i = 0; i < 10; i++){
            cartLineInfoCommand.incrementQuantityByOne();
        }
       assertEquals(cartLineInfoCommand.getQuantity(), 11);
    }

    @Test
    public void decreaseQuantityByOne() {
        for(int i = 0; i < 10; i++){
            cartLineInfoCommand.incrementQuantityByOne();
        }
        for(int i = 0; i < 15; i++){
            cartLineInfoCommand.decreaseQuantityByOne();
        }

        assertEquals(1, cartLineInfoCommand.getQuantity());

    }

    @Test
    public void equals() {
        assertEquals(cartLineInfoCommand, new CartLineInfoCommand(productInfoCommand, 1));
    }

}