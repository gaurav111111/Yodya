package com.yupexx.bazaar.api.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.ChatModel;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.repository.ChatRepository;
import com.yupexx.bazaar.api.service.interfaces.ChatInterface;

@Service
public class ChatService implements ChatInterface {

	@Autowired
	ChatRepository dao;
	
	@Autowired
	EntityManager em;
	
	@Override
	public List<ChatModel> getAllChats(UserModel user) {
		// TODO Auto-generated method stub
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("get_last_chat", ChatModel.class);
		System.out.println("query-----------------------"+query);
		query.registerStoredProcedureParameter("userid", Long.class, ParameterMode.IN);
		query.setParameter("userid",user.getId());
		query.execute();
		List<ChatModel> chats = (List<ChatModel>) query.getResultList();
		return chats;
	}

	@Override
	public Optional<ChatModel> getChatById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findByIdAndStatus(id,true);
	}
	
	@Override
	public List<ChatModel> getChatByChatId(Long chatId) {
		// TODO Auto-generated method stub
		return dao.findByChatIdAndStatus(chatId,true);
	}

	@Override
	public ChatModel addChat(ChatModel object) {
		// TODO Auto-generated method stub
		return dao.save(object);
	}
	
	@Override
	public ChatModel updateChat(Integer chatId,ChatModel object) {
		// TODO Auto-generated method stub
		ChatModel chat = dao.findById(chatId).orElseThrow(() -> new EntityNotFoundException());
		chat.setChatMessage(object.getChatMessage());
		return dao.save(chat);
	}
	
	@Override
	public ChatModel deleteChat(Integer chatId) {
		// TODO Auto-generated method stub
		ChatModel object = dao.findById(chatId).orElseThrow(() -> new EntityNotFoundException());
		object.setStatus(false);
		return dao.save(object);
	}

}
