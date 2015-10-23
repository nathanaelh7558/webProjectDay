package mvc_course.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

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
static Logger log = Logger.getLogger(WorldController.class.getName());

@RequestMapping(value = "/home.mvc", method = RequestMethod.GET)
public String home(Locale locale, Model model) {
	return "logIn";
}

@RequestMapping("/employees.mvc")
public String continents(Model m){
	m.addAttribute("employees", worldMapper.getEmployees());
	return "employees";
}
@RequestMapping("/projects.mvc")
public String continents2(Model m){
	m.addAttribute("projects", worldMapper.getProjects());
	return "projects";
}

@RequestMapping("/adminpage.mvc")
public String adminPage(Model m){
	m.addAttribute("employees", worldMapper.getEmployees());
	m.addAttribute("projects", worldMapper.getProjects());
	return "adminPage";
}
@RequestMapping("/adminpage2.mvc")
public String adminPage2(Model m){
	
	return "adminPage2";
}
@RequestMapping("/addProject.mvc")
public String addProject(Model m){
	return "addProject";
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
		if(setTitle(n1)&&setFname(n2)&&setLname(n3)&&setDob(n4)){
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
		return "newUserSuccess";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "manageEmployees2";
		}
		} else {
			return "manageEmployees2";
		}
}

@RequestMapping("/remove_employee_page.mvc")
public String removeEmployee(Model m){
	return "removeEmployee";
}
@RequestMapping("/delete_employee.mvc")
public String deleteEmployee(Model m,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("wassssapppp");
	String ID=request.getParameter("empId");
	Connection c;
	try {
		c = dataSource.getConnection();
		Statement s = c.createStatement();
		String sqlString = "CALL removeEmployee("+ID+");";
		s.executeUpdate(sqlString);			      
		return "adminPage2";	     
	} catch (Exception e) {
		e.printStackTrace();
		return "error";
	}
}

	@RequestMapping("/search_employee.mvc")
	public String searchEmployee(Model m,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("fNameInput");
		Connection c;
		try {
			c = dataSource.getConnection();	
			log.info(name);
			c = dataSource.getConnection();
			Statement s = c.createStatement();
			String sqlString = "SELECT fName FROM employee WHERE fName LIKE '%"+name+"%' ORDER BY 1;";
			ResultSet rs = s.executeQuery(sqlString);			      
				      if(rs.next()) {
				    	  System.out.println("GOINGIN HERE");
				    	  m.addAttribute("employees", worldMapper.searchEmployees(name));
				    		return "removeEmployeeSearch";  
				      } else {
				    	  m.addAttribute("errorMessages", "No results found, verify name");
				      }
				          
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		m.addAttribute("removeEmployee", worldMapper.getEmployeeByName(name));
		return "removeEmployee";
//		Connection c;
//		try {
//			c = dataSource.getConnection();
//			PreparedStatement stmt = c.prepareStatement("select employeeId, fName from employee where fName =?");
//			log.info(name);
//				      stmt.setString(1,name);
//				      ResultSet rs =  stmt.executeQuery();
//				      if(rs.next()) {
//				    	  while (rs.next()) {
//					    	  log.info("**********************");
//					      }  
//				      } else {
//				    	  m.addAttribute("errorMessages", "No results found, verify name");
//				      }
//				          
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "error";
//		}
//		
//		return "removeEmployee";
	}
	@RequestMapping("/search_employee2.mvc")
	public String searchEmployee2(Model m,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  log.info("*********entered*************");
		String name=request.getParameter("fNameInput");
		Connection c;
		try {
			c = dataSource.getConnection();	
			log.info(name);
			c = dataSource.getConnection();
			Statement s = c.createStatement();
			String sqlString = "SELECT fName FROM employee WHERE fName LIKE '%"+name+"%' ORDER BY 1;";
			ResultSet rs = s.executeQuery(sqlString);			      
				      if(rs.next()) {
				    	  System.out.println("GOINGIN HERE");
				    	  m.addAttribute("employees", worldMapper.searchEmployees(name));
				    		return "updateEmployeeSearch";  
				      } else {
				    	  m.addAttribute("errorMessages", "No results found, verify name");
				    	  return "updateEmployee";
				      }
				          
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		
	}


@RequestMapping("/update_employee_details.mvc")
public String updateEmployee(Model m){
	return "updateEmployee";
}

@RequestMapping("/acUpdate_employee.mvc")
public String acUpdateEmployee(Model m, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String ID=request.getParameter("empId");
	long temp = Long.parseLong(ID);
	m.addAttribute("employee", worldMapper.updateEmployee(temp));
	return "acUpdateEmployees";
}
@RequestMapping(value = "/newUpdateEmployee.mvc")
protected String doNewUpdateEmployee(Model m,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String n0=request.getParameter("empIdInput");
		String n1=request.getParameter("titleInput");
		String n2=request.getParameter("fNameInput");
		String n3=request.getParameter("lNameInput");
		String n4=request.getParameter("dobInput");
		String n5=request.getParameter("salaryInput");
		Connection c;
		if(setTitle(n1)&&setFname(n2)&&setLname(n3)&&setDob(n4)){
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
			int id= Integer.parseInt(n0);
			String sqlString = "CALL updateEmployee("+id+", '"+sqlDate+"',\""+n2+"\",\""+n3+"\",\""+n1+"\","+n5+");";
			System.out.println(sqlString);
			s.executeUpdate(sqlString);
		return "newUserSuccess";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "#";
		}
		} else {
			return "#";
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
			System.out.println(n2);
			System.out.println(n3);
			c = dataSource.getConnection();
			Statement s = c.createStatement();
			String sqlString = "SELECT username, password, category, name FROM credentials WHERE username = '"+n3+"' AND password = '"+n2+"';";
			ResultSet rs = s.executeQuery(sqlString);
			if(rs.next()){
				String category = rs.getString("category");
				if(category.equals("Admin")){
					m.addAttribute("adminName", rs.getString("name"));
					return "adminPage";
				} else if (category.equals("Finance")){
					return "Finance";
				}  else if (category.equals("Chris")){
					return "Chris";
				} else {
					return "logInError";
				}
			} else {
				System.out.println("Didnt log in");
				return "logInError";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "logInError";
		}
	
}

@RequestMapping("/view_payroll.mvc")
public String viewPayroll(Model m){
	return "financeEmployee";
}

public boolean setSalary(String salary) {
	if (salary.matches("[0-9]+") && salary.length() > 0) {
	return true;
	} else {

		return false;
	}
	
}

public boolean setTitle(String title) {
	if(title != null && !title.isEmpty()){
	return true;
	} else {
		return false;
	}
	
}

public boolean setLname(String lname) {
	if(lname != null && !lname.isEmpty()){
	return true;
	} else {
		return false;
	}
}

public boolean setFname(String fname) {
	if(fname != null && !fname.isEmpty()){
	return true;
	} else {
		return false;
	}
}

public boolean setDob(String dob) {
	if(convertDate(dob)){
	return true;
	} else {
		return false;
	}
}
public boolean convertDate(String x){
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	java.util.Date dob2 = null;
	java.sql.Date sqlDate = null;
	try {
		dob2 = formatter.parse(x);
		sqlDate = new java.sql.Date(dob2.getTime());
		return true;
	} catch (ParseException e) {
	return false;
	}
}

	public WorldController() {
		// TODO Auto-generated constructor stub
	}

}
