package tw.com.phctw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.com.phctw.model.Student;
import tw.com.phctw.service.StudentService;


@Controller
public class HelloController {
	@Autowired
	private StudentService service;
	
	@RequestMapping(value = "/hello")
	public String hello() {
		
		//CRUD
		//getById
//		Student student = service.getStudentBySid(3l);
//		System.out.println(student);
	    
		//selectAll
//		service.getAllStudents().forEach(System.out::println);
		
		//insert
//		Student student = new Student("aaan","aaam","aaac", "aaap");
//		System.out.println(service.insertStudent(student));
		
		//update
//		Student student = new Student("bbbn","aaam","aaac", "aaap");
//		student.setSid(1l);
//		System.out.println(service.updateStudent(student));
		
		//delete
		Long sid = 1l; 
		System.out.println(service.deleteStudentBySid(sid));
		
		
		return "hello";
	}
	
	
}
