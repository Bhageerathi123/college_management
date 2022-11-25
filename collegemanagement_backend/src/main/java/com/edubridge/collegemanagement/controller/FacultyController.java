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

import com.edubridge.collegemanagement.entities.Faculty;
import com.edubridge.collegemanagement.services.FacultyService;

@RestController
@RequestMapping("/faculty")
@DynamicUpdate
@CrossOrigin(origins = "http://localhost:4200")
public class FacultyController {
	@Autowired
	private FacultyService FacultyService;

	@PostMapping
	public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
		Faculty craeteFaculty = FacultyService.craeteFaculty(faculty);
		if (craeteFaculty != null && craeteFaculty.getFacultyId() > 0) {
			return ResponseEntity.ok(craeteFaculty);
		}
		return null;

	}

	@GetMapping
	public ResponseEntity<List<Faculty>> retrieveFacultys() {
		return (ResponseEntity<List<Faculty>>) ResponseEntity.ok()
				.body(FacultyService.retrieveFacultys());

	}

	@PutMapping("/{id}")
	public ResponseEntity<Faculty> updateFaculty(@PathVariable("id") long id, @RequestBody Faculty faculty) {
		faculty.setFacultyId(id);
		FacultyService.updateFaculty(faculty);
		return new ResponseEntity<>(faculty, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Faculty> getFaculty(@PathVariable("id") long facultyId) {
		return new ResponseEntity<>(FacultyService.getFaculty(facultyId), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteFaculty(@PathVariable("id") long facultyId) {
		FacultyService.deleteFaculty(facultyId);
		return new ResponseEntity<>(" Faculty deleted Successfully", HttpStatus.OK);

	}

}



