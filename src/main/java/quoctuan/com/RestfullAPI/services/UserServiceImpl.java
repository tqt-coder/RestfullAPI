package quoctuan.com.RestfullAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import quoctuan.com.RestfullAPI.dao.UserDAO;
import quoctuan.com.RestfullAPI.entities.UserEntity;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO useDao;
	
	@Override
	public UserEntity findByUser(String username) {
		// TODO Auto-generated method stub
		return useDao.findUser(username);
	}

	@Override
	public boolean checkLogin(UserEntity userForm) {
		// TODO Auto-generated method stub
		return useDao.checkLogin(userForm);
	}

	

}
