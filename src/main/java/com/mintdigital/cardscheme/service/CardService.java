package com.mintdigital.cardscheme.service;

import com.mintdigital.cardscheme.domain.CardDetails;
import com.mintdigital.cardscheme.model.CardResponseModel;
import com.mintdigital.cardscheme.model.CardVerificationResponse;
import com.mintdigital.cardscheme.model.PayloadStatResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CardService {

    Optional<CardVerificationResponse> verifyCard(String cardNumber);

   PayloadStatResponse getNumberOfHits(int start, int limit);


}
