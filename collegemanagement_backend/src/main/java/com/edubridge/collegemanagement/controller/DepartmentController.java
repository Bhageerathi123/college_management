package com.edubridge.collegemanagement.controller;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edubridge.collegemanagement.entities.Department;
import com.edubridge.collegemanagement.services.DepartmentService;

@RestController
@RequestMapping("/department")
@DynamicUpdate
@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@PostMapping
	public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
		Department craeteDepartment = departmentService.craeteDepartment(department);
		if (craeteDepartment != null) {
			return ResponseEntity.ok(craeteDepartment);
		}
		return null;

	}

	@GetMapping
	public ResponseEntity<List<Department>> retrieveDepartments() {
		return (ResponseEntity<List<Department>>) ResponseEntity.ok().header("Custom-Header", "foo")
				.body(departmentService.retrieveDepartments());

	}

	@PutMapping("/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable("id") long id,
			@RequestBody Department department) {
		department.setDepartmentId(id);
		departmentService.updateDepartment(department);
		return ResponseEntity.ok(departmentService.updateDepartment(department));

	}

	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartment(@PathVariable("id") long departmentId) {
		return new ResponseEntity<>(departmentService.getDepartment(departmentId), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable("id") long departmentId) {
		departmentService.deleteDepartment(departmentId);
		return new ResponseEntity<String>("Department is deleted successfully.!", HttpStatus.OK);

	}

}
