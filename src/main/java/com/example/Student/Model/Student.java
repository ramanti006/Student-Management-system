package com.example.Student.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String collgname;

	@Column
	private int rollno;
	
	@Column
	private String email;
	
	@Column
	private String phoneno;
	
	@Column
	private String department;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRollno() {
		return rollno;
	}

	public void setRollno(int rollno) {
		this.rollno = rollno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		
		this.department = department;
	}
	
	public String getCollgname() {
		return collgname;
	}

	public void setCollgname(String collgname) {
		this.collgname = collgname;
	}
	

	public Student() {
		super();
	}
	

	public Student(int id, String name, String collgname, int rollno, String email, String phoneno, String department) {
		super();
		this.id = id;
		this.name = name;
		this.collgname = collgname;
		this.rollno = rollno;
		this.email = email;
		this.phoneno = phoneno;
		this.department = department;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", collgname=" + collgname + ", rollno=" + rollno + ", email="
				+ email + ", phoneno=" + phoneno + ", department=" + department + "]";
	}

	
	
	
	
	

}
