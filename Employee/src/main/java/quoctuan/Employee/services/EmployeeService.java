package quoctuan.Employee.services;

import java.util.List;

import quoctuan.Employee.entities.EmployeeEntity;

public interface EmployeeService {
	List<EmployeeEntity> findAllEmployee();
	EmployeeEntity findByEmployeeById(int id);
}
