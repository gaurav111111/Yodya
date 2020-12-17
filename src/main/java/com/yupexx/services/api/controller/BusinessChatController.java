package com.yupexx.services.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.UserIdentity;
import com.yupexx.services.api.model.BusinessChatModel;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.services.api.service.BusinessChatService;

@RestController
public class BusinessChatController {
	@Autowired
	BusinessChatService service;
	
	@Autowired
	UserIdentity identity;

	@RequestMapping(value = "/services/chats", method = RequestMethod.GET)
	public List<BusinessChatModel> getChats(Authentication authentication) {
		System.out.println(this.getClass().getSimpleName() + " - Get all Chats service is invoked.");
		UserModel user = identity.get(authentication);
		return service.getAllChats(user);
	}
	
	@RequestMapping(value = "/services/chat/{id}", method = RequestMethod.GET)
	public Optional<BusinessChatModel> getChat(@PathVariable Integer id) {
		System.out.println(this.getClass().getSimpleName() + " - Get Respected Chat service is invoked.");
		return service.getChatById(id);
	}
	
	@RequestMapping(value = "/services/chat/chatId/{chatId}", method = RequestMethod.GET)
	public List<BusinessChatModel> getChatbyChatId(@PathVariable Long chatId) {
		System.out.println(this.getClass().getSimpleName() + " - Get Respected Chat service is invoked.");
		return service.getChatByChatId(chatId);
	}
	
	@RequestMapping(value = "/services/chat", method = RequestMethod.POST)
	public BusinessChatModel addChat(@Valid @RequestBody BusinessChatModel object) {
		System.out.println(this.getClass().getSimpleName() + " - New Chat service is invoked.");
		object.setChatId(this.getChatId(object));
		return service.addChat(object);
	}
	
	@RequestMapping(value = "/services/chat/{chatId}", method = RequestMethod.PUT)
	public BusinessChatModel updateChat(@Valid @PathVariable Integer chatId,@Valid @RequestBody BusinessChatModel object) {
		System.out.println(this.getClass().getSimpleName() + " - Update Chat service is invoked.");
		object.setChatId(this.getChatId(object));
		return service.updateChat(chatId,object);
	}
	
	@RequestMapping(value = "/services/chat/{chatId}", method = RequestMethod.DELETE)
	public BusinessChatModel deleteChat(@Valid @PathVariable Integer chatId) {
		System.out.println(this.getClass().getSimpleName() + " - Delete Chat service is invoked.");
		return service.deleteChat(chatId);
	}
	
	private Long getChatId(BusinessChatModel object) {
		if(object.getUserMasterId()>object.getCreatedBy()) {
			object.setChatId(Long.parseLong((Long.toString(object.getCreatedBy())+""+Long.toString(object.getUserMasterId()))));
		}else {
			object.setChatId(Long.parseLong((Long.toString(object.getUserMasterId())+""+Long.toString(object.getCreatedBy()))));
		}
		System.out.println(this.getClass().getSimpleName() + " - ChatId is "+object.getChatId());
		return object.getChatId();
	}
}
