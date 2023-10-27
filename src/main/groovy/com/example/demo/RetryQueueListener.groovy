package com.example.demo

import groovy.util.logging.Slf4j
import io.awspring.cloud.sqs.annotation.SqsListener
import org.springframework.stereotype.Component

@Slf4j
@Component
class RetryQueueListener {

    @SqsListener(queueNames = ['${queue.name}'])
    void receiveMessage(TestMessage message) {
        log.info("Received message from retry queue: $message")
    }
}
