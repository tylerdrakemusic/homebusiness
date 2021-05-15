package com.vt.fish.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private String sameAddress;
    private String shippingAddress;
    private String shippingAddress2;
    private String shippingCity;
    private String shippingName;
    private String shippingPhone;


}
