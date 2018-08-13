package org.khanal.SbHibernateShoppingcart.commands;

import org.khanal.SbHibernateShoppingcart.utils.PricingUtil;

import java.math.BigDecimal;
import java.util.Objects;

public class CartLineInfoCommand {
    private ProductInfoCommand productInfoCommand;
    private int quantity;

    public CartLineInfoCommand(ProductInfoCommand productInfoCommand, int quantity) {
        this.productInfoCommand = productInfoCommand;
        this.quantity = quantity;
    }

    public ProductInfoCommand getProductInfoCommand() {
        return productInfoCommand;
    }

    public void setProductInfoCommand(ProductInfoCommand productInfoCommand) {
        this.productInfoCommand = productInfoCommand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        BigDecimal bigDecimal =  this.productInfoCommand.getPrice().multiply(new BigDecimal(quantity)).setScale(PricingUtil.ROUNDING_SCALE, PricingUtil.ROUNDING_MODE);
        return bigDecimal;
    }

    public void incrementQuantityByOne() {
        quantity++;
    }

    public void decreaseQuantityByOne() {
        if(quantity > 1){
            quantity--;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartLineInfoCommand)) return false;
        CartLineInfoCommand that = (CartLineInfoCommand) o;
        return getQuantity() == that.getQuantity() &&
                Objects.equals(getProductInfoCommand(), that.getProductInfoCommand());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getProductInfoCommand(), getQuantity());
    }
}
