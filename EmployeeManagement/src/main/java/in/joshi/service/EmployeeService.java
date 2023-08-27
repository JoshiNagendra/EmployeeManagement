package in.joshi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.joshi.model.Employee;


@Service
public interface EmployeeService {
	
	public String registerService(Employee employee);
	public Employee fetchEmployeeById(Integer id);
    public List<Employee> fetchAllEmployees();
    public String deleteEmployeeById(Integer id);
    public String updateEmployeeDetails(Employee employee);
    
}
