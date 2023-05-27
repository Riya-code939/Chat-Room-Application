package com.example.Semester4Project.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker        //enables message broker
public class Config implements WebSocketMessageBrokerConfigurer
{
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry)
    {
        registry.addEndpoint("/server1").withSockJS();      //server1--> endpoint or url to connect with server
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry)
    {
        registry.enableSimpleBroker("/topic");       //to receive message--> /topic/return-to
        registry.setApplicationDestinationPrefixes("/app");         //to send message--> /app/message
    }


}
