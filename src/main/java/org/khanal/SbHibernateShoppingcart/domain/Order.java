package org.khanal.SbHibernateShoppingcart.domain;

import org.springframework.stereotype.Repository;

import javax.naming.Name;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", length = 50)
    private String id;

    private BigDecimal amount;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_email", length = 128, nullable = false)
    private String customerEmail;

    @Column(name = "customer_name", length = 255, nullable = false)
    private String customerName;

    @Column(name="customer_phone", length = 128, nullable = false)
    private String customerPhone;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "order_num")
    private Integer orderNum;

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
}
