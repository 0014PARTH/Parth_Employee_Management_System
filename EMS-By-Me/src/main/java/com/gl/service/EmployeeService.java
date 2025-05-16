package com.gl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.ui.Model;

import com.gl.model.Employee;
import com.gl.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository empRepo;

	public List<Employee> getEmployees() {
		return empRepo.findAll();
	}

	public void save(Employee emp) {
		empRepo.save(emp);
	}

	public Employee findEmpById(int empId) {
		return empRepo.findById(empId).get();
	}

	public void deleteById(int empId) {
		empRepo.deleteById(empId);
	}

	public List<Employee> searchEmployees(String keyword) {
		return empRepo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
	}

}
