package com.edubridge.collegemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edubridge.collegemanagement.entities.Faculty;

@Repository

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
