package quoctuan.com.RestfullAPI.controller;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import quoctuan.com.RestfullAPI.bean.AuthenticationResponse;
import quoctuan.com.RestfullAPI.bean.ResultBean;
import quoctuan.com.RestfullAPI.config.JwtUtil;
import quoctuan.com.RestfullAPI.entities.EmployeeEntity;
import quoctuan.com.RestfullAPI.entities.UserEntity;
import quoctuan.com.RestfullAPI.services.EmployeeServiceImpl;
import quoctuan.com.RestfullAPI.services.UserServiceImpl;

@RestController
public class RestAPI {

	
//	private static final Logger log = LoggerFactory.getLogger(RestAPI.class);

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	@Autowired
	private UserServiceImpl userDao;

	@RequestMapping(value= {"/rest/employees"}, method = RequestMethod.POST,
			produces= {
					MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE
	})
	public ResultBean restAddEmpoyee(@RequestBody EmployeeEntity e) {
		ResultBean rb = new ResultBean();
		try {
			employeeServiceImpl.saveEmployee(e);
			rb.setStatus(20);
			rb.setSuccess(true);
			rb.setError("No error");
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e);
		}
		return rb;
	}

	
	@RequestMapping(value= {"/rest/employees"}, method = RequestMethod.PUT,
			produces= {
					MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE
	})
	public ResultBean restUppdate(@RequestBody EmployeeEntity e) {
		employeeServiceImpl.saveEmployee(e);
		ResultBean rb = new ResultBean();
		rb.setStatus(200);
		rb.setSuccess(true);
		rb.setError("No error");
		return rb;
	}
	
	@RequestMapping(value= {"/rest/employees"}, method = RequestMethod.GET,
			produces= { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public List<EmployeeEntity> restListAll(Model model) {
		List<EmployeeEntity>ls = employeeServiceImpl.listAll();
		model.addAttribute("ls", ls);
		return ls;
	}
	
	
	@RequestMapping(value= {"/rest/employees/{id}"}, method=RequestMethod.DELETE,
			produces= { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE })
	public ResultBean deleteEmployee(@PathVariable("id") int id, Model model) {
		employeeServiceImpl.deleteEmployee(id);
		
		ResultBean rb = new ResultBean();
		rb.setStatus(200);
		rb.setSuccess(true);
		rb.setError("No error");
		
		model.addAttribute("rb", rb);
		
		return rb;
	}
	
	@RequestMapping(value={"/rest/login2"} ,method = RequestMethod.POST
			)
	public ResultBean checkLogin(@RequestBody UserEntity e, Model model){
		boolean re = userDao.checkLogin(e);
		System.out.println(re);
		ResultBean rb = new ResultBean();
		rb.setError("Login is " + re);
		rb.setSuccess(re);
		rb.setStatus(200);
		
		model.addAttribute("rb", rb);
		return rb;
	}

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping(value = "/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserEntity user) throws Exception {
		try{
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		}
		catch(BadCredentialsException e){
			throw new Exception("Incorrect username or password");
		}

		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
