package com.example.demo

import io.awspring.cloud.sqs.operations.SqsAsyncOperations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.messaging.Message
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @Autowired
    SqsAsyncOperations template

    @Value('${queue.name}')
    String queueName

    @GetMapping(path = "/test")
    ResponseEntity<TestMessage> retryRedact() {
        Optional<Message<TestMessage>> result = template.receiveAsync(queueName, TestMessage).get()
        return ResponseEntity.ok(result.get().payload)
    }
}
