package com.mintdigital.cardscheme.event;

import com.mintdigital.cardscheme.model.CardVerificationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PublisherBean {

    private Source source;

    public PublisherBean(Source source) {
        this.source = source;
    }

    public void publishCardScheme(CardVerificationResponse cardVerificationResponse){

        log.info("about to publish payload {} ", cardVerificationResponse);

        // Ready to publish the message
        source.output()
                .send(MessageBuilder
                        .withPayload(cardVerificationResponse)
                .build());

    }

}
