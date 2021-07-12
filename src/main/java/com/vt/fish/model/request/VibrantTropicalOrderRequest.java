package com.vt.fish.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Document(collection = "collection")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class VibrantTropicalOrderRequest {
    @NotEmpty
    private String billingAddress;
    private String billingAddress2;
    @NotEmpty
    private String billingCity;
    @Email
    private String billingEmail;
    private String billingName;
    private String billingPhone;
    @NotEmpty
    private String billingState;
    @NotEmpty
    private String billingZip;
    @NotEmpty
    private String cardName;
    @NotEmpty
    private String cardNumber;
    private String checkoutCheckbox;
    private String correlationId;
    @NotEmpty
    private String cvv;
    @NotEmpty
    private String expMonth;
    @NotEmpty
    private String expYear;
    private String policyCheckbox;
    private ArrayList<Product> products;
    @JsonIgnore
    private final String vibrantTropicalRequestId;
    @JsonIgnore
    private Date timeStamp;
    private String sameAddress;
    private String shippingAddress;
    private String shippingAddress2;
    private String shippingCity;
    private String shippingState;
    private String shippingZip;
    private String shippingName;
    private String shippingPhone;

    public VibrantTropicalOrderRequest() {
        vibrantTropicalRequestId = UUID.randomUUID().toString();
    }

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

    public String getBillingState() { return billingState; }

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

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        if(correlationId==null){
            UUID uuid = UUID.randomUUID();
            correlationId = uuid.toString();
        }
        this.correlationId = correlationId;
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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
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

    public String getShippingState() {return shippingState;}

    public String getShippingZip() {return shippingZip;}

    public String getShippingName() {
        return shippingName;
    }

    public String getShippingPhone() {
        return shippingPhone;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    public void setShippingZip(String shippingZip) {
        this.shippingZip = shippingZip;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public void setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    public String getVibrantTropicalRequestId() {
        return vibrantTropicalRequestId;
    }

    public double getTotalOrderPrice(){
        double price = 0;
        for(Product product:this.getProducts()){
            price += product.getDollars()*product.getQuantity();
        }
        return price;
    }
}
