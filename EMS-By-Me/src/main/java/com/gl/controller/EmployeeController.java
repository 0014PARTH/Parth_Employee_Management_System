package com.gl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.model.Employee;
import com.gl.service.EmployeeService;

@Controller
@RequestMapping("/ems")
public class EmployeeController {

	@Autowired
	EmployeeService empSvc;

	@RequestMapping("/list")
	public String getAllEmp(Model theModel) {

		List<Employee> emp = empSvc.getEmployees();
		theModel.addAttribute("emp", emp);

		return "emp-list";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		// We have to add new emp so we are goging to delcare empty obj
		Employee emp = new Employee();
		theModel.addAttribute("emp", emp);

		return "emp-form";
	}

	@RequestMapping("/save")
	public String saveEmp(@ModelAttribute("emp") Employee emp) {
		empSvc.save(emp);
		return "redirect:/ems/list";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("empId") int empId, Model theModel) {

		Employee emp = empSvc.findEmpById(empId);
		theModel.addAttribute("emp", emp);

		return "emp-form";
	}

	@RequestMapping("/delete")
	public String deleteById(@RequestParam("empId") int empId) {
		empSvc.deleteById(empId);
		return "redirect:/ems/list";
	}

	@RequestMapping("/search")
	public String search(@RequestParam("keyword") String keyword, Model theModel) {
		List<Employee> result = empSvc.searchEmployees(keyword);
		theModel.addAttribute("emp", result);
		return "emp-list";
	}
}
