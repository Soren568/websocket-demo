package com.alex.websocketdemo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.websocketdemo.models.ChatMessage;

@Service
public class ChatMessageServ {

	
	@Autowired
	ChatMessageRepo chatMessageRepo;
	
	public List<ChatMessage> findAll() {
		return chatMessageRepo.findAll();
	}
	
	public ChatMessage findById(Long id) {
		Optional<ChatMessage> chatMessage = chatMessageRepo.findById(id);
		if (chatMessage.isPresent()) {
			return chatMessage.get();
		} else {
			return null;
		}
	}
	
	public ChatMessage save(ChatMessage chatMessage) {
		return chatMessageRepo.save(chatMessage);
	}
	
	public void delete(Long id) {
		chatMessageRepo.deleteById(id);
	}
	
	
	
}