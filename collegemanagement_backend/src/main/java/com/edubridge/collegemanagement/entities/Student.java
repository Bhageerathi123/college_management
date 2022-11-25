package com.edubridge.collegemanagement.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "STUDENT")
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long studentId;

	@Column(length = 50)
	private String studentfName;
	@Column(length = 50)
	private String studentlName;
	
	
	@Column(unique = true, length = 100)
	private String contactEmail;
	@Column(unique = true, length = 13)
	private String contactPhone;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDateTime;

}
