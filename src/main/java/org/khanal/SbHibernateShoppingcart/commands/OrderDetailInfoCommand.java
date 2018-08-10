package org.khanal.SbHibernateShoppingcart.commands;

import java.math.BigDecimal;

public class OrderDetailInfoCommand {
    private String id;
    private BigDecimal amount;
    private BigDecimal price;
    private Integer quantity;
    private String productCode;
    private String productName;

    public OrderDetailInfoCommand() {
    }

    public OrderDetailInfoCommand(String id, BigDecimal amount, BigDecimal price, Integer quantity, String
            productCode, String productName) {
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.quantity = quantity;
        this.productCode = productCode;
        this.productName = productName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
