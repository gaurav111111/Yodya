package com.yupexx.services.api.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.services.api.model.BusinessChatModel;
import com.yupexx.services.api.repository.BusinessChatRepository;
import com.yupexx.services.api.service.interfaces.BusinessChatInterface;

@Service
public class BusinessChatService implements BusinessChatInterface {


	@Autowired
	BusinessChatRepository dao;
	
	@Autowired
	EntityManager em;
	
	@Override
	public List<BusinessChatModel> getAllChats(UserModel user) {
		// TODO Auto-generated method stub
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("get_last_chat", BusinessChatModel.class);
		query.registerStoredProcedureParameter("userid", Long.class, ParameterMode.IN);
		query.setParameter("userid",user.getId());
		query.execute();
		List<BusinessChatModel> chats = (List<BusinessChatModel>) query.getResultList();
		return chats;
	}

	@Override
	public Optional<BusinessChatModel> getChatById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findByIdAndStatus(id,true);
	}
	
	@Override
	public List<BusinessChatModel> getChatByChatId(Long chatId) {
		// TODO Auto-generated method stub
		return dao.findByChatIdAndStatus(chatId,true);
	}

	@Override
	public BusinessChatModel addChat(BusinessChatModel object) {
		// TODO Auto-generated method stub
		return dao.save(object);
	}
	
	@Override
	public BusinessChatModel updateChat(Integer chatId,BusinessChatModel object) {
		// TODO Auto-generated method stub
		BusinessChatModel chat = dao.findById(chatId).orElseThrow(() -> new EntityNotFoundException());
		chat.setChatMessage(object.getChatMessage());
		return dao.save(chat);
	}
	
	@Override
	public BusinessChatModel deleteChat(Integer chatId) {
		// TODO Auto-generated method stub
		BusinessChatModel object = dao.findById(chatId).orElseThrow(() -> new EntityNotFoundException());
		object.setStatus(false);
		return dao.save(object);
	}

}
