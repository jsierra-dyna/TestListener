package com.example.demo


import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory
import io.awspring.cloud.sqs.listener.acknowledgement.handler.AcknowledgementMode
import io.awspring.cloud.sqs.operations.SqsAsyncOperations
import io.awspring.cloud.sqs.operations.SqsTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient

@Configuration(proxyBeanMethods = false)
class QueueConfig {
    @Bean
    SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient
                .builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .region(Region.US_EAST_1)
                .build()
    }

    @Bean
    SqsMessageListenerContainerFactory<Object> defaultSqsListenerContainerFactory(SqsAsyncClient sqsAsyncClient) {
        return SqsMessageListenerContainerFactory
                .builder()
                .sqsAsyncClient(sqsAsyncClient)
                .configure {
                    it
                            .acknowledgementMode(AcknowledgementMode.ON_SUCCESS)
                }
                .build()
    }

    @Bean
    SqsAsyncOperations template(SqsAsyncClient sqsAsyncClient) {
        return SqsTemplate.newAsyncTemplate(sqsAsyncClient)
    }
}
