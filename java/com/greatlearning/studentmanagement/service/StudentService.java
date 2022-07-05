package com.greatlearning.studentmanagement.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.studentmanagement.model.Student;

@Service
public class StudentService {
	
	private SessionFactory factory;
	
	public List<Student> getAllStudents(){
		return factory.getCurrentSession().createQuery("from students").list();
		
	}
	@Autowired
	public StudentService(SessionFactory factory){
		this.factory=factory;
	}
	
	public static void main (String[] args) {
		
	}

}
