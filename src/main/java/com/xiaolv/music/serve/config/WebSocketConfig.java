package com.xiaolv.music.serve.config;

import com.xiaolv.music.serve.constants.WebSocketConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @ClassName: WebSocketConfig
 * @Description: TODO
 * @Author: lijinpeng
 * @Date: 2024/1/29 13:43
 * @Version: 1.0
 **/
//WebSocket的配置类
@Configuration
//开启对WebSocket的支持
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * @description: 配置消息代理（message broker）启用一个简单的基于内存的消息代理
     * @author: 李金澎
     * @date: 2024/1/29 14:23
     * @param: [config]
     * @return: void
     **/
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(WebSocketConstant.SIMPLE_BROKER);
        //send命令时需要带上/app前缀
        // 全局使用的消息前缀（客户端订阅路径上会体现出来）
        config.setApplicationDestinationPrefixes(WebSocketConstant.DESTINATION_PREFIXES);
        //修改convertAndSendToUser方法前缀
        //点对点使用的订阅前缀（客户端订阅路径上会体现出来），
        // 不设置的话，默认也是/user/
        //config.setUserDestinationPrefix ("/user/");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/gs-guide-websocket");
        registry
                // 配置连接前缀，客户端建立连接时：localhost:port/server
                .addEndpoint(WebSocketConstant.ENDPOINT)
                // 添加拦截器
//                .addInterceptors(jusicWebSocketHandshakeInterceptor)
                // 允许所有域
                .setAllowedOriginPatterns(WebSocketConstant.ALLOWED_ORIGINS)
//                .setAllowedOrigins("*")
                // 支持以 SockJs 的方式建立连接，这是一个备选方案，在 WebSocket 不可用的时候启用
                .withSockJS();
    }

}
