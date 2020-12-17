package com.yupexx.bazaar.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.bazaar.api.model.ChatModel;
import com.yupexx.bazaar.api.model.UserModel;

public interface ChatInterface {

	List<ChatModel> getAllChats(UserModel user);

	Optional<ChatModel> getChatById(Integer id);
	
	List<ChatModel> getChatByChatId(Long chatId);

	ChatModel addChat(ChatModel object);

	ChatModel updateChat(Integer chatId, ChatModel object);

	ChatModel deleteChat(Integer chatId);

}
