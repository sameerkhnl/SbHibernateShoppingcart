package org.khanal.SbHibernateShoppingcart.commands;

import org.khanal.SbHibernateShoppingcart.domain.Product;
import org.khanal.SbHibernateShoppingcart.utils.PricingUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CartInfoCommand {
    private List<CartLineInfoCommand> cartLineInfoCommandList = new ArrayList<>();
    private CustomerInfoCommand customerInfoCommand;
    private int orderNum;

    public CartInfoCommand(List<CartLineInfoCommand> cartLineInfoCommandList, CustomerInfoCommand customerInfoCommand) {
        this.cartLineInfoCommandList = cartLineInfoCommandList;
        this.customerInfoCommand = customerInfoCommand;
    }

    public CartInfoCommand() {
    }

    public List<CartLineInfoCommand> getCartLineInfoCommandList() {
        return cartLineInfoCommandList;
    }

    public void setCartLineInfoCommandList(List<CartLineInfoCommand> cartLineInfoCommandList) {
        this.cartLineInfoCommandList = cartLineInfoCommandList;
    }

    public CustomerInfoCommand getCustomerInfoCommand() {
        return customerInfoCommand;
    }

    public void setCustomerInfoCommand(CustomerInfoCommand customerInfoCommand) {
        this.customerInfoCommand = customerInfoCommand;
    }

    public void addItemToCart(ProductInfoCommand productInfoCommand) {
        if (this.findItemInCart(productInfoCommand) >= 0) {
            this.cartLineInfoCommandList.stream().filter(x -> x.getProductInfoCommand().getCode().equals
                    (productInfoCommand.getCode())).findFirst().get().incrementQuantityByOne();
        } else {
            this.cartLineInfoCommandList.add(new CartLineInfoCommand(productInfoCommand, 1));
        }
    }

    public void removeItemFromCart(int index) {
        this.cartLineInfoCommandList.remove(index);
    }

    public void subtractItemFromCart(ProductInfoCommand productInfoCommand) {
        int index = findItemInCart(productInfoCommand);
        if (index >= 0) {
            this.cartLineInfoCommandList.get(index).decreaseQuantityByOne();
        }
    }

    //returns the index of the intem in the list if exists otherwise returns -1
    public int findItemInCart(ProductInfoCommand productInfoCommand) {
        AtomicInteger index = new AtomicInteger(0);
        OptionalInt optionalInt =  IntStream.range(0, this.cartLineInfoCommandList.size()).filter(i -> cartLineInfoCommandList.get(i)
                .getProductInfoCommand().getCode().equals(productInfoCommand.getCode())).findFirst();
        if(optionalInt.isPresent()){
            return optionalInt.getAsInt();
        }
        return -1;
    }

//        if(optional.isPresent()) {
//            int i = 0;
//            cartLineInfoCommandList.stream().filter(x -> {
////                x.getProductInfoCommand().getCode().equals(productInfoCommand.getCode());
////            })
//
//
//                for (int i = 0; i < cartLineInfoCommandList.size(); i++) {
//                    if (optional.get().equals(cartLineInfoCommandList.get(i))) {
//                        return i;
//                    }
//                }
//            }
//            return -1;
//        }

    public BigDecimal getTotalAmount() {
        Set<BigDecimal> bigDecimals = new HashSet<>();
        this.cartLineInfoCommandList.stream().forEach(x -> {
            bigDecimals.add(x.getAmount());
        });

        BigDecimal bigDecimal = bigDecimals.stream().reduce(BigDecimal::add).get();
        bigDecimal.setScale(PricingUtil.ROUNDING_SCALE, PricingUtil.ROUNDING_MODE);
        return bigDecimal;
    }
}
