package com.gency.subscribe.web.controller.business;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.gency.subscribe.model.business.SocketMessage;

@Controller
public class WebSocketController {

	
	 @MessageMapping("/send") //当浏览器向服务端发送请求时,通过@MessageMapping映射/welcome这个地址,类似于@ResponseMapping
     @SendTo("/topic/send")//当服务器有消息时,会对订阅了@SendTo中的路径的浏览器发送消息
     public String say(SocketMessage message) {
         try {
        	
         } catch (Exception e) {
             e.printStackTrace();
         }
         return "hello";
     }
}
