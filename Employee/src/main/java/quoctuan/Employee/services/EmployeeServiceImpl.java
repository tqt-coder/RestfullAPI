package quoctuan.Employee.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quoctuan.Employee.dao.EmployeeRespository;
import quoctuan.Employee.entities.EmployeeEntity;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	 EmployeeRespository employeeRespository;
	@Override
	public List<EmployeeEntity> findAllEmployee() {
		// TODO Auto-generated method stub
		
		return employeeRespository.findAll();
	}
	@Override
	public EmployeeEntity findByEmployeeById(int id) {
		// TODO Auto-generated method stub
		return employeeRespository.findById(id).get();
	}
	@Override
	public void addEmployee(EmployeeEntity e) {
		employeeRespository.save(e);
		
	}
	@Override
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		EmployeeEntity e = new EmployeeEntity();
		e = employeeRespository.findById(id).get();
		employeeRespository.delete(e);
		
	}

}
