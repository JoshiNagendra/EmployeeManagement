package in.joshi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.joshi.model.Employee;
import in.joshi.service.EmployeeService;

@RestController
@CrossOrigin(value = "http://192.168.1.21:3000")
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@PostMapping("/register")
	public ResponseEntity<?> registerEmployee(@RequestBody Employee employee) {

		String msg = service.registerService(employee);
		logger.info("Register Method is called for Registering the Employee");
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	@GetMapping("/find")
	public ResponseEntity<?> getEmployeeById(@RequestParam("id") Integer id) 
	{
		logger.info("/getEmloyeeById called to Find the Employee By ID");
		Employee data = service.fetchEmployeeById(id);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> getAllEmployees() {

		logger.info("/findAll is called to Find All Employees ");
		List<Employee> list = service.fetchAllEmployees();

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@DeleteMapping("/deleteEmployee")
	public ResponseEntity<?> deleteEmployeeById(@RequestParam("id") Integer id) {
        logger.info("/delete is called to Delete the Employee");
		String msg = service.deleteEmployeeById(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateEmployeeById(@RequestBody Employee employee) {
        logger.info("/update is called to Update the Employee");
		String msg = service.updateEmployeeDetails(employee);
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	

}
