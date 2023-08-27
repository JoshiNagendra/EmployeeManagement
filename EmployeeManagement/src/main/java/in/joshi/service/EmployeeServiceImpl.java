package in.joshi.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import in.joshi.dao.EmployeeDao;
import in.joshi.model.Employee;
import in.joshi.util.ImageHelper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao dao;

	@Autowired
	private ImageHelper helper;
	
	private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public String registerService(Employee employee) {
		logger.info("registerService Methos is executing");
		byte[] imageBytes = Base64.getDecoder().decode(employee.getImageBase64());
		employee.setImageBase64("Sucess");
		employee.setImageUrl(helper.saveImageToStorage(imageBytes));

		dao.save(employee);
		
		

		return "Employee Registerd Sucessfully";
	}

	@Override
	public Employee fetchEmployeeById(Integer id)  {
		logger.info("fetchEmployeeById is executing ");
		Optional<Employee> optional = dao.findById(id);
		if (optional.isPresent()) {
			Employee employee = optional.get();
			
			Resource resource = new FileSystemResource(employee.getImageUrl());
	        byte[] imageBytes = null;
			try {
				imageBytes = StreamUtils.copyToByteArray(resource.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
	        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			
			employee.setImageBase64(base64Image);
			return employee;
		} else {
			return null;
		}

	}

	@Override
	public List<Employee> fetchAllEmployees() {
		
		logger.info("fetchAllEmployee method is executing");
		List<Employee> list = dao.findAll();
		list.sort((t1, t2) -> t1.getId().compareTo(t2.getId()));
		for(int i=0;i<list.size();i++) {
			
			Employee employee = list.get(i);
			Resource resource = new FileSystemResource(employee.getImageUrl());
	        byte[] imageBytes = null;
			try {
				imageBytes = StreamUtils.copyToByteArray(resource.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
	        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			
			employee.setImageBase64(base64Image);
			
		}
		return list;
	}

	@Override
	public String deleteEmployeeById(Integer id) {
		logger.info("delteEmployeeId is executing");
		Optional<Employee> optional = dao.findById(id);
		if (optional.isPresent()) {
			dao.deleteById(id);
			return "Employee Recorde Deleted Succesfully";
		} else {
			return "Employee Record Not Found For ID : " + id;
		}
	}

	@Override
	public String updateEmployeeDetails(Employee employee) {
        logger.info("updateEmployeeDetails method is executing");
		Optional<Employee> optional = dao.findById(employee.getId());
		if (optional.isPresent()) {
			byte[] imageBytes = Base64.getDecoder().decode(employee.getImageBase64());
			employee.setImageBase64("Sucess");
			employee.setImageUrl(helper.saveImageToStorage(imageBytes));
			dao.save(employee);
			return "Employee Details Got Updated Sucessfully For ID : " + employee.getId();
		} else {
			return "Employee Record Not Found for Deletion";
		}
	}
}
