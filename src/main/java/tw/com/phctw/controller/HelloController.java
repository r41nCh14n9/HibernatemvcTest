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
//		Long sid = 1l; 
//		System.out.println(service.deleteStudentBySid(sid));
		
		String sacc = "PHCTWSTUDENT000003";
		String spwd = "123456";
		Student student = new Student();
		student.setSacc(sacc);
		student.setSpwd(spwd);
		System.out.println("check : "+service.validateStudent(student));
		
//		System.out.println("sacc = " + sacc);
//		boolean exist = service.checkSaccExist(sacc);
//		System.out.println("check : "+exist);
		
		return "hello";
	}
	
	
}
