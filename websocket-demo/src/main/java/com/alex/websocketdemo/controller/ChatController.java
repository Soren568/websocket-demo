package com.alex.websocketdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.alex.websocketdemo.ChatMessageServ;
import com.alex.websocketdemo.models.ChatMessage;

@Controller
public class ChatController {
	
	@Autowired
	ChatMessageServ serv;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("messages", serv.findAll());
		return "index.jsp";
	}
	@GetMapping("/test")
	public String test() {
		return "test.jsp";
	}
	
	
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
    	serv.save(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, 
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        
        return chatMessage;
    }

}
