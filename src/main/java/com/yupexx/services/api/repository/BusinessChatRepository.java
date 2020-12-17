package com.yupexx.services.api.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yupexx.services.api.model.BusinessChatModel;

@Repository
public interface BusinessChatRepository extends JpaRepository<BusinessChatModel, Integer>{

	List<BusinessChatModel> findByStatus(Boolean b);
	
	@Transactional
	@Procedure("ResourceType.get_last_chat")
	Object[] findQuery(@Param("userid") Long userid);

	Optional<BusinessChatModel> findByIdAndStatus(Integer id, boolean b);
	
	List<BusinessChatModel> findByChatIdAndStatus(Long chatId, boolean b);
}