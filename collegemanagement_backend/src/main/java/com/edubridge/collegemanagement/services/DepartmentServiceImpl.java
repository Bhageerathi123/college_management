package com.edubridge.collegemanagement.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edubridge.collegemanagement.entities.Department;
import com.edubridge.collegemanagement.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department craeteDepartment(Department department) {
		if (department != null) {
			department.setCreationDateTime(new Date());
			department.setUpdatedDateTime(new Date());
		}
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> retrieveDepartments() {
		return departmentRepository.findAll();
	}

	@Override
	public Department updateDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public Department getDepartment(long departmentId) {
		Optional<Department> findById = departmentRepository.findById(departmentId);
		return findById.isPresent() ? findById.get() : null;
	}

	@Override
	public String deleteDepartment(long departmentId) {
		try {
			departmentRepository.deleteById(departmentId);
		} catch (Exception ex) {
			logger.error("Unable to delete the department");

		}
		return "Success";
	}

}
