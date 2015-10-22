package mvc_course.controllers;

import mvc_course.models.Student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

	public StudentController() {
		// TODO Auto-generated constructor stub
	}
	@RequestMapping(value="setup.mvc")
	public String setupStudents() {
		Student s;
		s = new Student();
		s.setFirstName("Matt");
		s.setLastName("Smith");
		s.setGender("MALE");
		s.setUniversity("Edinburgh Napier");
		Student.list.add(s);
		s = new Student();
		s.setFirstName("Nathanael");
		s.setLastName("Holmes");
		s.setGender("MALE");
		s.setUniversity("Edinburgh");
		Student.list.add(s);
		s = new Student();
		s.setFirstName("A");
		s.setLastName("Girl");
		s.setGender("FEMALE");
		s.setUniversity("Edinburgh");
		Student.list.add(s);
		return "redirect:listStudents.mvc";
	}
	@RequestMapping(value="listStudents.mvc")
	public ModelAndView listStudents(ModelAndView mav) {
		mav.setViewName("studentList");
		mav.addObject("studentList", Student.list);
		return mav;
	}

	


}
