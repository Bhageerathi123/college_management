package com.edubridge.collegemanagement.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edubridge.collegemanagement.entities.Faculty;
import com.edubridge.collegemanagement.repository.FacultyRepository;

@Service

public class FacultyServiceImpl implements FacultyService {

	Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

	@Autowired
	private FacultyRepository facultyRepository;

	@Override
	public Faculty craeteFaculty(Faculty faculty) {
		if (faculty != null) {
			faculty.setCreationDateTime(new Date());
			faculty.setUpdatedDateTime(new Date());
		}
		return facultyRepository.save(faculty);
	}

	@Override
	public List<Faculty> retrieveFacultys() {
		return facultyRepository.findAll();
	}

	@Override
	public Faculty updateFaculty(Faculty faculty) {
		return facultyRepository.save(faculty);
	}

	@Override
	public Faculty getFaculty(long facultyId) {
		Optional<Faculty> findById = facultyRepository.findById(facultyId);
		return findById.isPresent() ? findById.get() : null;
	}

	@Override
	public String deleteFaculty(long facultyId) {
		try {
			facultyRepository.deleteById(facultyId);
		} catch (Exception ex) {
			logger.error("Unable to delete the Faculty" , ex);

		}
		return "Success";
	}

}
