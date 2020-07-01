package tw.com.phctw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import tw.com.phctw.model.Student;
import tw.com.phctw.service.StudentService;

@Controller
public class LoginController {

	@Autowired
	StudentService service;

	@GetMapping(value = "/login")
	public ModelAndView showLogin(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView("login");
		System.out.println("login...");
		mv.addObject("student", new Student());

		return mv;
	}

	@PostMapping(value = "/loginProcess")
	public ModelAndView loginProcess(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("student") Student student) {
		System.out.println("in Process...");
		ModelAndView mv = null;

		Student s = service.validateStudent(student);
//		System.out.println(s);
		
		if (s == null) {
			mv = new ModelAndView("login");
			mv.addObject("message", "AccountName or Password is wrong!!");
//		} else if (s.getConfirm().compareTo("1")!=0) {
////			System.out.println("compare : "+s.getConfirm().compareTo("1"));
//			mv = new ModelAndView("login");
//			mv.addObject("message", "Account is not confirmed yet!!");
		} else {
			Long sid = s.getSid();
			mv = new ModelAndView("redirect:/student/get/" + sid);
			mv.addObject("student", s);
		}

		return mv;
	}

}
