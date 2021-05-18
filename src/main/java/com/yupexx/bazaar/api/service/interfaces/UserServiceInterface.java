package com.yupexx.bazaar.api.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.model.view.SellerAdCountVwModel;


public interface UserServiceInterface {
	
	public List<UserModel> getUsers();
	public Optional getUserById(Long userId);
	public UserModel addUser(UserModel user);
	public UserModel updateUser(UserModel user);
	public UserModel deleteUser(UserModel user);
	public UserModel findByEmail(String email);
	List<SellerAdCountVwModel> getSimilarSellers();
	
	public void updateGoogleId(String gId, long id);
	
	

}
