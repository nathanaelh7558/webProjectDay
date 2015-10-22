package mvc_course.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import mvc_data.IWorldMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	return "logIn";
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

@RequestMapping("/newuser.mvc")
public String goToEmployee(Model m){
	return "addEmployee";
}
@RequestMapping(value = "/newEmployee.mvc")
protected String doNewEmployee(Model m,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String n1=request.getParameter("titleInput");
		String n2=request.getParameter("fNameInput");
		String n3=request.getParameter("lNameInput");
		String n4=request.getParameter("dobInput");
		String n5=request.getParameter("salaryInput");
		Connection c;
		try {
			
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date dob = null;
				java.sql.Date sqlDate = null;
				try {
					dob = formatter.parse(n4);
					sqlDate = new java.sql.Date(dob.getTime());
				} catch (ParseException e) {
						
				}
			System.out.println(sqlDate);
			c = dataSource.getConnection();
			Statement s = c.createStatement();
			String sqlString = "CALL insertEmployee('"+sqlDate+"',\""+n2+"\",\""+n3+"\",\""+n1+"\","+n5+");";
			System.out.println(sqlString);
			s.executeUpdate(sqlString);
		return "user created";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
}





@RequestMapping("/newproject.mvc")
public String goToProject(Model m){
	return "addProject";
}
@RequestMapping(value = "/newProject.mvc")
protected String doNewProject(Model m,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String n1=request.getParameter("pNameInput");
		String n2=request.getParameter("startDateInput");
		String n3=request.getParameter("endDateInput");
		Connection c;
		try {
			
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				//java.util.Date dob = null;
				java.util.Date startDate = null;
				java.util.Date endDate = null;
				java.sql.Date sqlDate = null;
				try {
					startDate = formatter.parse(n2);
					endDate = formatter.parse(n3);
					//sqlDate = new java.sql.Date(dob.getTime());
				} catch (ParseException e) {
						
				}
			System.out.println(sqlDate);
			c = dataSource.getConnection();
			Statement s = c.createStatement();
			String sqlString = "CALL addProject('"+n1+"',\""+startDate+"\",\""+endDate+"\");";
			//System.out.println(sqlString);
			s.executeUpdate(sqlString);
		return "user created";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	
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
			System.out.println(n2);
			System.out.println(n3);
			c = dataSource.getConnection();
			Statement s = c.createStatement();
			String sqlString = "SELECT username, password, category, name FROM credentials WHERE username = '"+n3+"' AND password = '"+n2+"';";
			ResultSet rs = s.executeQuery(sqlString);
			if(rs.next()){
				String category = rs.getString("category");
				if(category.equals("Admin")){
					return "Admin";
				} else if (category.equals("Finance")){
					return "Finance";
				}  else if (category.equals("Chris")){
					return "Chris";
				} else {
					return "error";
				}
			} else {
				System.out.println("Didnt log in");
				return "error";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	
}
	public WorldController() {
		// TODO Auto-generated constructor stub
	}

}
