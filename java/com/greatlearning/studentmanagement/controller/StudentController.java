package com.greatlearning.studentmanagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.studentmanagement.model.Student;
import com.greatlearning.studentmanagement.service.StudentService;


@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/list")
	public String listBooks(Model theModel) {
		
		System.out.println("Request Received");
		
		// get books from database
		List<Student> theStudents = studentService.findAll();
		
		//add to the spring model
		theModel.addAttribute("Students",theStudents);
		
		//send over to the form
		return "list-Students";	
	}
	
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Student theStudent = new Student();
		theModel.addAttribute("Student",theStudent);
		
		//send over to the form
		return "Student-Form";	
	}
	
	@RequestMapping("/showFormUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {
		
		//get the book from the service
		Student theStudent = studentService.findById(theId);
		
		//set book as a model attribute to pre-populate the form
		theModel.addAttribute("Student",theStudent);
		
		//send over to the form
		return "Student-Form";
	}
	
	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("department") String department,
			@RequestParam("country") String country) {
		
		System.out.println(id);
		Student theStudent;
		if(id != 0) {
			theStudent = studentService.findById(id);
			theStudent.setName(name);
			theStudent.setDepartment(department);
			theStudent.setCountry(country);
		} else {
			theStudent = new Student();
			
			//save the book
			studentService.save(theStudent);
			
			//use a redirect to prevent duplicate submissions
			
			}
		return "redirect:/student/list";
		}
		
		@RequestMapping("/delete")
		public String delete(@RequestParam("studentId") int theId) {
			
			//delete the book
			studentService.deleteById(theId);
			
			//redirect to /Books/list
			return "redirect:student/list";
			
		}
		
}
	
