package com.mintdigital.cardscheme.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseModel {

    private CountryModel country;

    private BankModel bank;

    private String scheme;

    private String prepaid;

    private String type;

    private String brand;

}
