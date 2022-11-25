package com.edubridge.collegemanagement.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edubridge.collegemanagement.entities.Student;
import com.edubridge.collegemanagement.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student craeteStudent(Student Student) {
		if (Student != null) {
			Student.setCreationDateTime(new Date());
			Student.setUpdatedDateTime(new Date());
		}
		return studentRepository.save(Student);
	}

	@Override
	public List<Student> retrieveStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student getStudent(long studentId) {
		Optional<Student> findById = studentRepository.findById(studentId);
		return findById.isPresent() ? findById.get() : null;
	}

	@Override
	public String deleteStudent(long studentId) {
		try {
			studentRepository.deleteById(studentId);
		} catch (Exception ex) {
			logger.error("Unable to delete the Student");

		}
		return "Success";
	}

}
