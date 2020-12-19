package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chosen_payment_method")
public class ChosenPaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "card_number", length = 20)
    private String cardNumber;

    @Column(length = 3)
    private String cvc;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    @OneToMany(mappedBy = "chosenPaymentMethod")
    private List<Order> orderList;

    public ChosenPaymentMethod() {
    }

    public long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCustomer(Customer customer) {
        setUser(customer);
    }

    public void setAdmin(Administrator administrator) {
        setUser(administrator);
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
