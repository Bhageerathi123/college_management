package com.edubridge.collegemanagement.services;

import java.util.List;

import com.edubridge.collegemanagement.entities.Faculty;

public interface FacultyService {

	public Faculty craeteFaculty(Faculty faculty);

	public List<Faculty> retrieveFacultys();

	public Faculty updateFaculty(Faculty faculty);

	public Faculty getFaculty(long facultyId);

	public String deleteFaculty(long facultyId);

}
