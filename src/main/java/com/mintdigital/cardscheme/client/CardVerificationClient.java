package com.mintdigital.cardscheme.client;

import com.google.gson.Gson;
import com.mintdigital.cardscheme.model.CardResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class CardVerificationClient {

    @Value("${card.lookup.url}")
    private String cardLookUpUrl;

    private MessageProcessor messageProcessor;


    private Gson gson;

    public CardVerificationClient(MessageProcessor messageProcessor, Gson gson) {
        this.messageProcessor = messageProcessor;
        this.gson = gson;
    }




    public Optional<CardResponseModel> cardLookup(String cardNumber){

        log.info("calling the lookup service to verify a card number {} ", cardNumber);

        String lookupUrl = cardLookUpUrl+cardNumber;

        log.info("lookup url {} ", lookupUrl);

        WebServiceResponse webServiceResponse = null;

        try {
            webServiceResponse = messageProcessor.sendHttpsRequest2(lookupUrl);
        } catch (IOException e) {
            log.error("connection error to service {} ", e.getMessage());

        }

        if(webServiceResponse != null){
            log.info("webservice is not null, check for the status code");

            if(webServiceResponse.getResponseCode() == 200){
                CardResponseModel cardResponseModel = gson.fromJson(webServiceResponse.getResponseMessage(),
                        CardResponseModel.class);

                log.info("card response model {} ", cardResponseModel);

                return Optional.of(cardResponseModel);
            }
        }

        return Optional.empty();


    }
}
