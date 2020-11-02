package com.mintdigital.cardscheme.service.impl;

import com.mintdigital.cardscheme.client.CardVerificationClient;
import com.mintdigital.cardscheme.event.PublisherBean;
import com.mintdigital.cardscheme.exception.CardVerificationException;
import com.mintdigital.cardscheme.model.BankModel;
import com.mintdigital.cardscheme.model.CardResponseModel;
import com.mintdigital.cardscheme.model.CardVerificationResponse;
import com.mintdigital.cardscheme.model.CountryModel;
import com.mintdigital.cardscheme.repository.CardDetailsRepository;
import com.mintdigital.cardscheme.service.CardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CardServiceImplTest {

    @Mock
    CardVerificationClient cardVerificationClient;

    @Mock
    PublisherBean publisherBean;

    @Mock
    CardDetailsRepository cardDetailsRepository;

    CardService cardService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cardService = new CardServiceImpl(cardVerificationClient, cardDetailsRepository, publisherBean);
    }

    @Test
    @DisplayName("Test Card Verification")
    void verifyCard() {

        String cardNumber = "539983";

        CountryModel countryModel = new CountryModel();
        countryModel.setAlpha2("NG");
        countryModel.setCurrency("NGN");
        countryModel.setLatitude("10");
        countryModel.setLongitude("8");
        countryModel.setName("Nigeria");
        countryModel.setNumeric("");

        BankModel bankModel = new BankModel();
        bankModel.setCity("");
        bankModel.setName("GTBANK");
        bankModel.setPhone("2348039003900");
        bankModel.setUrl("");


        CardResponseModel cardResponseModel = new CardResponseModel();
        cardResponseModel.setBank(bankModel);
        cardResponseModel.setBrand("Debit");
        cardResponseModel.setScheme("mastercard");
        cardResponseModel.setType("debit");
        cardResponseModel.setCountry(countryModel);


        when(cardVerificationClient.cardLookup(cardNumber)).thenReturn(Optional.of(cardResponseModel));

        Optional<CardVerificationResponse> response = cardService.verifyCard(cardNumber);

        assertThat(response).isPresent();

        verify(cardVerificationClient).cardLookup(cardNumber);
    }


    @Test
    @DisplayName("test card verification exception")
    void testCardVerificationException(){
        Assertions.assertThrows(CardVerificationException.class, ()->{
            cardService.verifyCard("10000");
        });
    }
}