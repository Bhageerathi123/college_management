package com.edubridge.collegemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edubridge.collegemanagement.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
