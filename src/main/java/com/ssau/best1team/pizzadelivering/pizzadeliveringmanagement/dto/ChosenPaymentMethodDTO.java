package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChosenPaymentMethodDTO {

    private long id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String cardNumber;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String cvc;
    private PaymentMethodDTO paymentMethod;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}
