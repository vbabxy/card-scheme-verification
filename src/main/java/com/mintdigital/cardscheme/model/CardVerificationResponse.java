package com.mintdigital.cardscheme.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardVerificationResponse {

    private String scheme;

    private String type;

    private String bank;

    private String cardNumber;
}
