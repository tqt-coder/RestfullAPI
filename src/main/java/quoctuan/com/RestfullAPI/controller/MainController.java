package quoctuan.com.RestfullAPI.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import quoctuan.com.RestfullAPI.bean.EmployeeBean;
import quoctuan.com.RestfullAPI.entities.EmployeeEntity;
import quoctuan.com.RestfullAPI.services.EmployeeServiceImpl;

@Controller
public class MainController {
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	@GetMapping("/home")
	public String homePage(Model model) {
		List<EmployeeEntity>lsEmp1= employeeServiceImpl.listAll();
		List<EmployeeBean>lsEmpl2 = new ArrayList<EmployeeBean>();
	
		for (EmployeeEntity ls : lsEmp1) {
			EmployeeBean eb = new EmployeeBean();
			eb.setId(ls.getId());
			eb.setName(eb.getName());
			eb.setPassword(eb.getPassword());
			lsEmpl2.add(eb);
		}
		model.addAttribute("lsEmp",lsEmpl2);
		return "index";
	}

	@GetMapping(value="/token/{name}")
	@ResponseBody
	public String usingToken(@PathVariable("name") String name){

		return "<h2>Hello"+"</h2>";
	}
	
	
}
