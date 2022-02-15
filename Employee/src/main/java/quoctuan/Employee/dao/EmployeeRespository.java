package quoctuan.Employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import quoctuan.Employee.entities.EmployeeEntity;


public interface EmployeeRespository extends JpaRepository<EmployeeEntity, Integer> {

}
