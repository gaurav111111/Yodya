package com.yupexx.bazaar.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.UserIdentity;
import com.yupexx.bazaar.api.model.ChatModel;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.service.ChatService;

@RestController
@CrossOrigin
public class ChatController {
	@Autowired
	ChatService service;
	
	@Autowired
	UserIdentity identity;

	@RequestMapping(value = "/bazaar/chats", method = RequestMethod.GET)
	public List<ChatModel> getChats(Authentication authentication) {
		System.out.println(this.getClass().getSimpleName() + " - Get all Chats service is invoked.");
		UserModel user = identity.get(authentication);
		System.out.println("user-------------------------"+user.toString());
		return service.getAllChats(user);
	}
	
	@RequestMapping(value = "/bazaar/chat/{id}", method = RequestMethod.GET)
	public Optional<ChatModel> getChat(@PathVariable Integer id) {
		System.out.println(this.getClass().getSimpleName() + " - Get Respected Chat service is invoked.");
		return service.getChatById(id);
	}
	
	@RequestMapping(value = "/bazaar/chat/chatId/{chatId}", method = RequestMethod.GET)
	public List<ChatModel> getChatbyChatId(@PathVariable Long chatId) {
		System.out.println(this.getClass().getSimpleName() + " - Get Respected Chat service is invoked.");
		return service.getChatByChatId(chatId);
	}
	
	@RequestMapping(value = "/bazaar/chat", method = RequestMethod.POST)
	public ChatModel addChat(@Valid @RequestBody ChatModel object) {
		System.out.println(this.getClass().getSimpleName() + " - New Chat service is invoked.");
		object.setChatId(this.getChatId(object));
		return service.addChat(object);
	}
	
	@RequestMapping(value = "/bazaar/chat/{chatId}", method = RequestMethod.PUT)
	public ChatModel updateChat(@Valid @PathVariable Integer chatId,@Valid @RequestBody ChatModel object) {
		System.out.println(this.getClass().getSimpleName() + " - Update Chat service is invoked.");
		object.setChatId(this.getChatId(object));
		return service.updateChat(chatId,object);
	}
	
	@RequestMapping(value = "/bazaar/chat/{chatId}", method = RequestMethod.DELETE)
	public ChatModel deleteChat(@Valid @PathVariable Integer chatId) {
		System.out.println(this.getClass().getSimpleName() + " - Delete Chat service is invoked.");
		return service.deleteChat(chatId);
	}
	
	private Long getChatId(ChatModel object) {
		if(object.getUserMasterId()>object.getCreatedBy()) {
			object.setChatId(Long.parseLong((Long.toString(object.getCreatedBy())+""+Long.toString(object.getUserMasterId()))));
		}else {
			object.setChatId(Long.parseLong((Long.toString(object.getUserMasterId())+""+Long.toString(object.getCreatedBy()))));
		}
		System.out.println(this.getClass().getSimpleName() + " - ChatId is "+object.getChatId());
		return object.getChatId();
	}
}
