package com.yupexx.bazaar.api.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.ChatModel;

@Repository
public interface ChatRepository extends JpaRepository<ChatModel, Integer>{

	List<ChatModel> findByStatus(boolean b);
	
	//@Query("select v from ChatModel as v where v.id in ( SELECT max(id) FROM ChatModel where createdBy = :createdBy or userMasterId= :createdBy group by chatId)")
	@Transactional
	@Procedure("ResourceType.get_last_chat")
	Object[] findQuery(@Param("userid") Long userid);

	Optional<ChatModel> findByIdAndStatus(Integer id, boolean b);
	
	List<ChatModel> findByChatIdAndStatus(Long chatId, boolean b);

	List<ChatModel> findByChatId(Long chatId);
}