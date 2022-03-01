package quoctuan.com.RestfullAPI.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import quoctuan.com.RestfullAPI.entities.UserEntity;

@Repository
public class UserDAO {
	@Autowired
	private EntityManager entityManager;
	
	
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);

	
	@SuppressWarnings("unchecked")
	public boolean checkLogin(UserEntity userForm) {
		log.info("username: ",userForm.getUsername());
		String sql = "select e from "+ UserEntity.class.getName()+" e where e.username=:username"
				+ " and e.password = :password";
		Query query = entityManager.createQuery(sql, UserEntity.class);
	
		query.setParameter("username",userForm.getUsername());
		
		query.setParameter("password", userForm.getPassword());
		
		List<UserEntity> ls = query.getResultList();
		
	log.info("test{}",ls);
		
		
		if(ls != null && ls.size() > 0) {
			return true;
		}
		return false;
		
	}
	
	public UserEntity findUser(String username) {
		String hql = "select e from "+ UserEntity.class.getName()+ " e where e.username = :username";
		
		Query query = entityManager.createQuery(hql, String.class);
		
		query.setParameter("username", username);
		
		UserEntity newUser = new UserEntity();
		newUser = (UserEntity) query.getSingleResult();
		
		return newUser;
	}
}
