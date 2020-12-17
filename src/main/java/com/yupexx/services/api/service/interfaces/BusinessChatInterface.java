package com.yupexx.services.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.services.api.model.BusinessChatModel;
import com.yupexx.bazaar.api.model.UserModel;

public interface BusinessChatInterface {

	List<BusinessChatModel> getAllChats(UserModel user);

	Optional<BusinessChatModel> getChatById(Integer id);
	
	List<BusinessChatModel> getChatByChatId(Long chatId);

	BusinessChatModel addChat(BusinessChatModel object);

	BusinessChatModel updateChat(Integer chatId, BusinessChatModel object);

	BusinessChatModel deleteChat(Integer chatId);

}
