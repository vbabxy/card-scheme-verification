package com.mintdigital.cardscheme.service.impl;

import com.mintdigital.cardscheme.client.CardVerificationClient;
import com.mintdigital.cardscheme.domain.CardDetails;
import com.mintdigital.cardscheme.event.PublisherBean;
import com.mintdigital.cardscheme.exception.BadRequestException;
import com.mintdigital.cardscheme.exception.CardVerificationException;
import com.mintdigital.cardscheme.model.CardResponseModel;
import com.mintdigital.cardscheme.model.CardVerificationResponse;
import com.mintdigital.cardscheme.model.PayloadStatResponse;
import com.mintdigital.cardscheme.repository.CardDetailsRepository;
import com.mintdigital.cardscheme.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CardServiceImpl implements CardService {

    private CardVerificationClient cardVerificationClient;

    private CardDetailsRepository cardDetailsRepository;

    private PublisherBean publisherBean;

    public CardServiceImpl(CardVerificationClient cardVerificationClient, CardDetailsRepository cardDetailsRepository, PublisherBean publisherBean) {
        this.cardVerificationClient = cardVerificationClient;
        this.cardDetailsRepository = cardDetailsRepository;
        this.publisherBean = publisherBean;
    }

    @Override
    public Optional<CardVerificationResponse> verifyCard(String cardNumber) {
        log.info("about to verify card number {} ", cardNumber);

        CardResponseModel cardResponseModel = cardVerificationClient.cardLookup(cardNumber)
                .orElseThrow(()-> new CardVerificationException("card number does not exist"));

        log.info("card response Model {} ", cardResponseModel);

        CardVerificationResponse cardVerificationResponse = CardVerificationResponse.builder()
                .bank(cardResponseModel.getBank().getName())
                .type(cardResponseModel.getType())
                .scheme(cardResponseModel.getScheme())
                .cardNumber(cardNumber)
                .build();

        publisherBean.publishCardScheme(cardVerificationResponse);

        return Optional.of(cardVerificationResponse);

    }

    @Override
    public PayloadStatResponse getNumberOfHits(int start, int limit) {

        log.info("about getting number of hits");

        int pageNumber = start -1;

        if(pageNumber < 0){
            throw new BadRequestException("start cannot be zero or less than zero");
        }

        Pageable pageable = PageRequest.of(pageNumber,limit);

        Page<CardDetails> cardDetails = cardDetailsRepository.findAllCards(pageable);

        Map<String, Long> result = cardDetails.getContent().stream()
                .collect(Collectors.toMap(CardDetails::getCardNumber,
                        CardDetails::getNumberOfHits));

        return PayloadStatResponse.builder()
                .limit(limit)
                .start(start)
                .size(cardDetails.getTotalElements())
                .payload(result)
                .build();


    }

}
