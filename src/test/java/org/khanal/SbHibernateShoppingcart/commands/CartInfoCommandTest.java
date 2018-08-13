package org.khanal.SbHibernateShoppingcart.commands;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class CartInfoCommandTest {
    private List<CartLineInfoCommand> cartLineInfoCommandList;
    private CustomerInfoCommand customerInfoCommand;
    private int orderNum;
    private CartInfoCommand cartInfoCommand;

    @Before
    public void setup() {
        cartLineInfoCommandList = new ArrayList<>();
        customerInfoCommand = new CustomerInfoCommand("customer1", "address1", "email1@example.com", "1111111", true);

        ProductInfoCommand productInfoCommand1 = new ProductInfoCommand("abc", "product1", 5.55);
        ProductInfoCommand productInfoCommand2 = new ProductInfoCommand("def", "product2", 98798798.59);
        ProductInfoCommand productInfoCommand3 = new ProductInfoCommand("ghi", "product3", 6577.54);
        ProductInfoCommand productInfoCommand4 = new ProductInfoCommand("jkl", "product4", 98.00);
        ProductInfoCommand productInfoCommand5 = new ProductInfoCommand("mno", "product5", 987878.98989);

        List<ProductInfoCommand> productInfoCommandsList = new ArrayList<>();
        productInfoCommandsList.add(productInfoCommand1);
        productInfoCommandsList.add(productInfoCommand2);
        productInfoCommandsList.add(productInfoCommand3);
        productInfoCommandsList.add(productInfoCommand4);
        productInfoCommandsList.add(productInfoCommand5);

        productInfoCommandsList.stream().forEach(x -> {
            cartLineInfoCommandList.add(new CartLineInfoCommand(x, productInfoCommandsList.indexOf(x) + 1));
        });

        cartInfoCommand = new CartInfoCommand(cartLineInfoCommandList, customerInfoCommand);
    }

    @Test
    public void addItemToCart() {
        cartInfoCommand.addItemToCart(new ProductInfoCommand("pqr", "product6", 9879889.99));
        cartInfoCommand.addItemToCart(new ProductInfoCommand("pqr", "product6", 9879889.99));
        cartInfoCommand.addItemToCart(new ProductInfoCommand("pqr", "product6", 9879889.99));
        cartInfoCommand.addItemToCart(new ProductInfoCommand("pqr", "product6", 9879889.99));
        assertEquals(6, cartInfoCommand.getCartLineInfoCommandList().size());
        cartInfoCommand.getCartLineInfoCommandList().stream().filter(x -> x.getProductInfoCommand().getCode().equals
                ("pqr"));
        assertEquals(4, cartInfoCommand.getCartLineInfoCommandList().stream().filter(x -> x.getProductInfoCommand()
                .getCode().equals("pqr")).findFirst().get().getQuantity());
    }

    @Test
    public void removeItemFromCart() {
        cartInfoCommand.removeItemFromCart(2);
        assertEquals(4, cartLineInfoCommandList.size());
    }

    @Test
    public void subtractItemFromCart() {
        cartInfoCommand.addItemToCart(new ProductInfoCommand("pqr", "product6", 9879889.99));
        cartInfoCommand.addItemToCart(new ProductInfoCommand("pqr", "product6", 9879889.99));
        cartInfoCommand.addItemToCart(new ProductInfoCommand("pqr", "product6", 9879889.99));
        cartInfoCommand.addItemToCart(new ProductInfoCommand("pqr", "product6", 9879889.99));
        cartInfoCommand.subtractItemFromCart(new ProductInfoCommand("pqr", "product6", 9879889.99));
        cartInfoCommand.subtractItemFromCart(new ProductInfoCommand("def", "product2", 98798798.59));
        cartInfoCommand.subtractItemFromCart(new ProductInfoCommand("def", "product2", 98798798.59));
        cartInfoCommand.subtractItemFromCart(new ProductInfoCommand("def", "product2", 98798798.59));
        cartInfoCommand.subtractItemFromCart(new ProductInfoCommand("def", "product2", 98798798.59));
        assertEquals(3, cartInfoCommand.getCartLineInfoCommandList().stream().filter(x -> x.getProductInfoCommand().getCode().equals("pqr")).findFirst().get().getQuantity());
        assertEquals(1, cartInfoCommand.getCartLineInfoCommandList().stream().filter(x -> x.getProductInfoCommand().getCode().equals("def")).findFirst().get().getQuantity());
    }

    @Test
    public void findItemInCart() {
        assertEquals(2, cartInfoCommand.findItemInCart(new ProductInfoCommand("ghi", "product3", 6577.54)));
        assertEquals(4, cartInfoCommand.findItemInCart(new ProductInfoCommand("mno", "product5", 987878.98989)));
    }

    @Test
    public void getTotalAmount() {
    }
}