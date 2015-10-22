package mvc_course.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import mvc_data.IWorldMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WorldController {
	
@Autowired
public IWorldMapper worldMapper;

@Autowired
private DataSource dataSource;

@RequestMapping(value = "/home.mvc", method = RequestMethod.GET)
public String home(Locale locale, Model model) {
	return "login";
}

@RequestMapping("/employees.mvc")
public String continents(Model m){
	m.addAttribute("employees", worldMapper.getEmployees());
	m.addAttribute("projects", worldMapper.getProjects());
	return "employees";
}

@RequestMapping("/adminpage.mvc")
public String adminPage(Model m){
	m.addAttribute("employees", worldMapper.getEmployees());
	m.addAttribute("projects", worldMapper.getProjects());
	return "adminPage";
}

@RequestMapping("/manageemployees.mvc")
public String manageEmployees(Model m){
	m.addAttribute("employees", worldMapper.getEmployees());
	m.addAttribute("projects", worldMapper.getProjects());
	return "manageEmployees";
}

@RequestMapping("/manageprojects.mvc")
public String manageProjects(Model m){
	m.addAttribute("employees", worldMapper.getEmployees());
	m.addAttribute("projects", worldMapper.getProjects());
	return "manageProjects";
}

@RequestMapping(value = "/hello.mvc")
protected String doPost(Model m,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String n2=request.getParameter("password");
		String n3=request.getParameter("usernameInput");
		Connection c;
		try {
			System.out.println("hey");
			c = dataSource.getConnection();
			Statement s = c.createStatement();
			String sqlString = "SELECT username, passcode FROM credentials WHERE username = '"+n3+"' AND passcode = '"+n2+"';";
			ResultSet rs = s.executeQuery(sqlString);
			if(rs.next()){
				m.addAttribute("msg", rs.getString("name"));
				System.out.println("logged in");
				return "continents";
			} else {
				System.out.println("Didnt log in");
				return "continents";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "employees.mvc";
		}
	
}
	public WorldController() {
		// TODO Auto-generated constructor stub
	}

}
