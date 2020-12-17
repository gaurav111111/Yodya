package com.yupexx.bazaar.api.repository;

import java.util.List;

import javax.persistence.NamedNativeQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yupexx.bazaar.api.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

	UserModel findByEmail(String email);

	
	@Query(value="select u.* ,(select count(ad.id) from ad_post_user as ad) as nAds from user_master as u",nativeQuery = true)
	List<UserModel> findAllUsers();

}
