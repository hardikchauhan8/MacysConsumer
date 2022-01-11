package com.macys.macysordermessageconsumer.configuration;

import com.google.pubsub.v1.ProjectSubscriptionName;
import com.macys.macysordermessageconsumer.utils.GCPConstants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class GCPConsumerConfig {

    //    @Bean
//    public PubSubInboundChannelAdapter messageChannelAdapter(
//            @Qualifier("pubsubInputChannel") MessageChannel inputChannel,
//            PubSubTemplate pubSubTemplate) {
//        PubSubInboundChannelAdapter adapter =
//                new PubSubInboundChannelAdapter(pubSubTemplate, "FirstTopicPubSub-sub");
//        adapter.setOutputChannel(inputChannel);
//        adapter.setAckMode(AckMode.MANUAL);
//
//        return adapter;
//    }

//    @Bean
//    @ServiceActivator(inputChannel = "pubsubInputChannel")
//    public MessageHandler messageReceiver() {
//        return message -> {
//            System.out.println("Message arrived! Payload: " + new String((byte[]) message.getPayload()));
//            BasicAcknowledgeablePubsubMessage originalMessage =
//                    message.getHeaders().get(GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class);
//            if (originalMessage != null) {
//                originalMessage.ack();
//            }
//        };
//    }
}
