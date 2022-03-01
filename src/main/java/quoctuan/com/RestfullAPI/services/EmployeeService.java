package quoctuan.com.RestfullAPI.services;

import java.util.List;

import quoctuan.com.RestfullAPI.entities.EmployeeEntity;

public interface EmployeeService {
	List<EmployeeEntity> listAll();
	void saveEmployee(EmployeeEntity e);
	void deleteEmployee(int id);
	EmployeeEntity findEmployee(int id);
}
