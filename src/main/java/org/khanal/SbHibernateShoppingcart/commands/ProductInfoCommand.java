package org.khanal.SbHibernateShoppingcart.commands;

import org.khanal.SbHibernateShoppingcart.utils.PricingUtil;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

public class ProductInfoCommand {
    private String code;
    private String name;
    private BigDecimal price;


    private byte[] image;

    public ProductInfoCommand(String code, String name, BigDecimal price, byte[] image) {
        this.code = code;
        this.name = name;
        this.image= image;
        this.setPrice(price);
    }

    public ProductInfoCommand(String code, String name, Double price, byte[] image) {
        this.code = code;
        this.name = name;
        this.image = image;
        this.setPrice(new BigDecimal(price.doubleValue()));
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        this.price.setScale(PricingUtil.ROUNDING_SCALE, PricingUtil.ROUNDING_MODE);
    }

    public void setPrice(Double price){
        this.setPrice(new BigDecimal(price.doubleValue()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductInfoCommand)) return false;
        ProductInfoCommand that = (ProductInfoCommand) o;
        return Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCode(), getName(), getPrice());
    }
}
