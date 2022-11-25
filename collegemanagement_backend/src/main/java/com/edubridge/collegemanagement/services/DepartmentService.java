package com.edubridge.collegemanagement.services;

import java.util.List;

import com.edubridge.collegemanagement.entities.Department;

public interface DepartmentService {

	public Department craeteDepartment(Department department);

	public List<Department> retrieveDepartments();

	public Department updateDepartment(Department department);

	public Department getDepartment(long departmentId);

	public String deleteDepartment(long departmentId);

}
