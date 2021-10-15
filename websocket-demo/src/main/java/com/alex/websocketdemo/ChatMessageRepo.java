package com.alex.websocketdemo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.alex.websocketdemo.models.ChatMessage;

public interface ChatMessageRepo extends CrudRepository<ChatMessage, Long> {

	List<ChatMessage> findAll();
	
}
