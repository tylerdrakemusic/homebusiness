package com.vt.fish.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class VibrantTropicalOrderRequest {
    private String billingAddress;
    private String billingAddress2;
    private String billingCity;
    private String billingEmail;
    private String billingName;
    private String billingPhone;
    private String billingZip;
    private String cardName;
    private String cardNumber;
    private String checkoutCheckbox;
    private String cvv;
    private String expMonth;
    private String expYear;
    private String policyCheckbox;
    private ArrayList<Product> products;
    private String sameAddress;
    private String shippingAddress;
    private String shippingAddress2;
    private String shippingCity;
    private String shippingName;
    private String shippingPhone;

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getBillingAddress2() {
        return billingAddress2;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public String getBillingEmail() {
        return billingEmail;
    }

    public String getBillingName() {
        return billingName;
    }

    public String getBillingPhone() {
        return billingPhone;
    }

    public String getBillingZip() {
        return billingZip;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCheckoutCheckbox() {
        return checkoutCheckbox;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public String getPolicyCheckbox() {
        return policyCheckbox;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getSameAddress() {
        return sameAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getShippingAddress2() {
        return shippingAddress2;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public String getShippingName() {
        return shippingName;
    }

    public String getShippingPhone() {
        return shippingPhone;
    }
}
