package in.joshi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.joshi.model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer>{

}
