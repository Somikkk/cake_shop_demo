package com.sheryians.major.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PaymentForm {
    private String cardNumber;
    private String cardHolder;
    private String expirationMonth;
    private String expirationYear;
    private String cvv;



}

