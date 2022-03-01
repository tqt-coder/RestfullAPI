package quoctuan.com.RestfullAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quoctuan.com.RestfullAPI.entities.EmployeeEntity;

@Repository
public interface EmployeeDAO extends JpaRepository<EmployeeEntity, Integer>{

}
