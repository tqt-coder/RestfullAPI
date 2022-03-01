package quoctuan.com.RestfullAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quoctuan.com.RestfullAPI.dao.EmployeeDAO;
import quoctuan.com.RestfullAPI.entities.EmployeeEntity;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDAO employeeRepository;
	@Override
	public List<EmployeeEntity> listAll() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}
	
	@Override
	public void saveEmployee(EmployeeEntity e) {
		// TODO Auto-generated method stub
		employeeRepository.save(e);
	}
	
	@Override
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		EmployeeEntity e = findEmployee(id);
		employeeRepository.delete(e);
	}
	public EmployeeEntity findEmployee(int id) {
		// TODO Auto-generated method stub
		return employeeRepository.getById(id);
	}

}
