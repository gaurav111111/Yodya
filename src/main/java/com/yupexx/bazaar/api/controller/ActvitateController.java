package com.yupexx.bazaar.api.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yupexx.bazaar.api.model.UserModel;
import com.yupexx.bazaar.api.repository.UserRepository;

@RestController
@CrossOrigin
public class ActvitateController {

	
	@Autowired
	private UserRepository userDao;
	
	@RequestMapping(value = "/bazaar/activate", method = RequestMethod.POST)
	public ResponseEntity<?> activateUser(@Valid @RequestBody UserModel user) throws Exception {
		UserModel user1 = userDao.findByEmail(user.getEmail());
		if(user1!=null){
			Date now = new Date();
			Date time = user1.getCreatedDate();
			long min=time.getMinutes();
			System.out.println("min--------------------------------------------"+min);
//			HashMap<String, String> map = new HashMap<>();
//			map.put("error", "Email Already Exists");
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		}
	
		return ResponseEntity.ok(true);
	}
}
