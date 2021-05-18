package com.yupexx.bazaar.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.yupexx.bazaar.api.UserIdentity;
import com.yupexx.bazaar.api.model.RoleModel;
import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.model.request.UpdateUserRequest;
import com.yupexx.bazaar.api.model.response.Message;
import com.yupexx.bazaar.api.model.view.SellerAdCountVwModel;
import com.yupexx.bazaar.api.repository.SellerAdCountVwRepository;
import com.yupexx.bazaar.api.repository.UserRepository;
import com.yupexx.bazaar.api.service.interfaces.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {
	@Autowired
    UserRepository dao;
	
	@Autowired
    SellerAdCountVwRepository daoSeller;
	
	@Autowired
	UserIdentity identity;
	
	@Autowired
	RoleService roleService;

	@Override
	public List<UserModel> getUsers() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	
	@Override
	public List<SellerAdCountVwModel> getSimilarSellers() {
		// TODO Auto-generated method stub
		
		List<SellerAdCountVwModel> data1=daoSeller.findAll();
		List<SellerAdCountVwModel> data2=new ArrayList();
		for(SellerAdCountVwModel sa:data1) {
		//	System.out.println("sa"+sa.getId());
			
			if(sa.getId()!=null) {
				Optional<UserModel> user = dao.findById(sa.getId());
				if(user.get()!=null && user.get().getRoleType()!=null) {
					Optional<RoleModel> role= roleService.findRole(user.get().getRoleType());
					 if(role.get()!=null && !(role.get().getRoleName().isEmpty()) && role.get().getRoleName().equalsIgnoreCase("admin")) {
						 
					 }else {
						 data2.add(sa);
					 }
				}else {
					 data2.add(sa);
				}
			}
			 else {
				 data2.add(sa);
			 }
		}
		
		
		return data2;
	}

	@Override
	public Optional getUserById(Long userId) {
		// TODO Auto-generated method stub
		return dao.findById(userId);
	}

	@Override
	public UserModel addUser(UserModel user) {
		// TODO Auto-generated method stub
		return user; // restricting of adding
		//return dao.save(user);
	}

	@Override
	public UserModel updateUser(UserModel user) {
		// TODO Auto-generated method stub
		//user.setPassword(null);
		
		return dao.save(user);
	}

	@Override
	public UserModel deleteUser(UserModel user) {
		// TODO Auto-generated method stub
		user.setStatus(false);
		return dao.save(user);
	}
	
	@Override
	public UserModel findByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.findByEmail(email);
	}

	public List<UserModel> getUsers(UserModel user) {
		// TODO Auto-generated method stub
		List<UserModel> user1 = dao.findAll();
		List<UserModel> user2 = new ArrayList();
		for(UserModel us : user1) {
			if(us.getId()!=user.getId()) {
				user2.add(us);
			}
		}
		return user2;
	}

	public Optional<UserModel> findById(long id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}
	
	public String userRoleCheck(Authentication authentication) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " - User Role check is invoked.");
		Message message = new Message();
		UserModel user = identity.get(authentication);
		if (user == null) {
			throw new Exception("User doesn't Exists");
		}
		else {
			if(user.getRoleType()!=null) {
				Optional<RoleModel> role= roleService.findRole(user.getRoleType());
				if(role!=null) {
					if(role.get().getRoleName().equalsIgnoreCase("admin")) {		
							
					return "admin";
				}
			}
		}
		
	  }
		return "No Role Tagged";
	}

	@Override
	public void updateGoogleId(String gId, long id) {
		// TODO Auto-generated method stub
		
		 dao.updateGoogleId(gId, id);
		
		
		
	}
}
