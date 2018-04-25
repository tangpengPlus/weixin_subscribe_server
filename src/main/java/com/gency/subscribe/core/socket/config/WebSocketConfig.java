package com.gency.subscribe.core.socket.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 
 * 作者:唐鹏
 * 描述: websocket配置
 * 版本: version 1.0.0
 * 时间: 2017年1月22日 下午3:18:16
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	 @Override
     public void registerStompEndpoints(StompEndpointRegistry registry) {//注册STOMP协议的节点(endpoint),并映射指定的url
         //注册一个STOMP的endpoint,并指定使用SockJS协议
         registry.addEndpoint("/endpointAric").withSockJS();

     }

     @Override
     public void configureMessageBroker(MessageBrokerRegistry registry) {//配置消息代理(Message Broker)
         //广播式应配置一个/topic消息代理
         registry.enableSimpleBroker("/topic");

     }
}
