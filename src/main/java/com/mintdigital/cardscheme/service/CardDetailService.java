package com.mintdigital.cardscheme.service;

import com.mintdigital.cardscheme.domain.CardDetails;
import com.mintdigital.cardscheme.domain.dto.CardDetailsDto;

import java.util.List;
import java.util.Optional;

public interface CardDetailService {


    List<CardDetails> getAllCardDetails();

}
