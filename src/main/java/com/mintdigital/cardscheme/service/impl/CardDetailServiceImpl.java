package com.mintdigital.cardscheme.service.impl;

import com.mintdigital.cardscheme.domain.CardDetails;
import com.mintdigital.cardscheme.domain.dto.CardDetailsDto;
import com.mintdigital.cardscheme.domain.xtraz.AppConstant;
import com.mintdigital.cardscheme.repository.CardDetailsRepository;
import com.mintdigital.cardscheme.service.CardDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CardDetailServiceImpl implements CardDetailService {

    private CardDetailsRepository cardDetailsRepository;

    public CardDetailServiceImpl(CardDetailsRepository cardDetailsRepository) {
        this.cardDetailsRepository = cardDetailsRepository;
    }

    @Override
    public List<CardDetails> getAllCardDetails() {
        log.info("about to get all cards available");

        return cardDetailsRepository.findAll();
    }




}
