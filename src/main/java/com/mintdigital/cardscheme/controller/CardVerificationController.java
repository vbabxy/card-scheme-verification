package com.mintdigital.cardscheme.controller;


import com.mintdigital.cardscheme.model.AppResponse;
import com.mintdigital.cardscheme.model.CardVerificationResponse;
import com.mintdigital.cardscheme.model.PayloadStatResponse;
import com.mintdigital.cardscheme.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
@Produces(MediaType.APPLICATION_JSON_VALUE)
@Consumes(MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class CardVerificationController {

    private CardService cardService;

    public CardVerificationController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(path = "/card-scheme/verify/{cardNumber}")
    public ResponseEntity<AppResponse> cardVerification(@PathVariable String cardNumber) {
        log.info("about to get lookup a card for customer {} ", cardNumber);

        Optional<CardVerificationResponse> cardVerificationResponse = cardService.verifyCard(cardNumber);

        if (cardVerificationResponse.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(AppResponse.builder()
                            .success(true)
                            .payload(cardVerificationResponse.get())
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(AppResponse.builder()
                            .success(false)
                            .payload("")
                            .build());
        }
    }


    @GetMapping(path = "/card-scheme/stats")
    public ResponseEntity<AppResponse> getNumberOfHits(@RequestParam("start") int start,
                                                        @RequestParam("limit") int limit) {

        PayloadStatResponse payloadStatResponse = cardService.getNumberOfHits(start, limit);


        return ResponseEntity.status(HttpStatus.OK)
                .body(AppResponse.builder()
                        .success(true)
                        .size(payloadStatResponse.getSize())
                        .limit(payloadStatResponse.getLimit())
                        .start(payloadStatResponse.getStart())
                        .payload(payloadStatResponse.getPayload())
                        .build());

    }
}
