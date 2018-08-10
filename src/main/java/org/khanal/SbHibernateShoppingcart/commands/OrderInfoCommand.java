package org.khanal.SbHibernateShoppingcart.commands;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderInfoCommand {
    private String id;
    private BigDecimal amount;
    private String customerAddress;
    private String customerEmail;
    private String customerName;
    private String customerPhone;
    private Date orderDate;
    private Integer orderNum;
    private List<OrderDetailInfoCommand> details;

    public OrderInfoCommand(String id, BigDecimal amount, String customerAddress, String customerEmail, String
            customerName, String customerPhone, Date orderDate, Integer orderNum, List<OrderDetailInfoCommand>
            details) {
        this.id = id;
        this.amount = amount;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.orderDate = orderDate;
        this.orderNum = orderNum;
        this.details = details;
    }

    public OrderInfoCommand() {
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

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public List<OrderDetailInfoCommand> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailInfoCommand> details) {
        this.details = details;
    }
}
