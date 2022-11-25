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

import com.edubridge.collegemanagement.entities.Student;
import com.edubridge.collegemanagement.services.StudentService;


@RestController
@RequestMapping("/student")
@DynamicUpdate
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student craeteStudent = studentService.craeteStudent(student);
		if (craeteStudent != null ) {
			return new ResponseEntity<>(craeteStudent, HttpStatus.OK);
		}
		return null;

	}

	@GetMapping
	public ResponseEntity<List<Student>> retrieveStudents() {
		return (ResponseEntity<List<Student>>) ResponseEntity.ok()
				.body(studentService.retrieveStudents());

	}

	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
		student.setStudentId(id);
		studentService.updateStudent(student);
		return new ResponseEntity<>(student, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable("id") long studentId) {
		return new ResponseEntity<>(studentService.getStudent(studentId), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") long studentId) {
		studentService.deleteStudent(studentId);
		return new ResponseEntity<>(" Student deleted Successfully", HttpStatus.OK);

	}

}
