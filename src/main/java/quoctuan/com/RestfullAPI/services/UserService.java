package quoctuan.com.RestfullAPI.services;

import java.util.List;

import quoctuan.com.RestfullAPI.entities.UserEntity;

public interface UserService {
	UserEntity findByUser(String username);
	
	boolean checkLogin(UserEntity userForm);
}
