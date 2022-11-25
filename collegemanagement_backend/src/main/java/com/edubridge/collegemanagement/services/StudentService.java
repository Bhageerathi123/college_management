package com.edubridge.collegemanagement.services;

import java.util.List;

import com.edubridge.collegemanagement.entities.Student;

public interface StudentService {

	public Student craeteStudent(Student student);

	public List<Student> retrieveStudents();

	public Student updateStudent(Student student);

	public Student getStudent(long studentId);

	public String deleteStudent(long studentId);

}
