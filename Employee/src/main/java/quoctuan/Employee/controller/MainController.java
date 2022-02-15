package quoctuan.Employee.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import quoctuan.Employee.entities.EmployeeEntity;
import quoctuan.Employee.services.EmployeeServiceImpl;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String welcome(HttpServletRequest rqs) {
		//rqs.setAttribute("msg", environment.getProperty("message"));
		return "welcome";
	}
	
	@GetMapping("/welcomes")
	@ResponseBody
	public String hello() {
		return "Hello world";
	}
	@Autowired
	EmployeeServiceImpl employeeServiceImpl;
	@GetMapping("/employees")
	public String list(Model model) {
		List<EmployeeEntity> lsEmployee = employeeServiceImpl.findAllEmployee();
		model.addAttribute("lsEmployee",lsEmployee );
		return "list";
	}
}
