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
@RequestMapping("/removeProject.mvc")
public String removeProject(Model m){
	return "removeProject";
}

@RequestMapping("/viewProjectsWithoutEmployees.mvc")
public String viewProjectsWithoutEmployees(Model m){
	return "viewProjectsWithoutEmployees";
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
@RequestMapping("/delete_project.mvc")
public String deleteProject(Model m,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("wassssapppp");
	String ID=request.getParameter("pID");
	Connection c;
	try {
		c = dataSource.getConnection();
		Statement s = c.createStatement();
		String sqlString = "CALL removeProject("+ID+");";
		s.executeUpdate(sqlString);			      
		return "adminPage2";	     
	} catch (Exception e) {
		e.printStackTrace();
		return "error";
	}
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
	}
	@RequestMapping("/search_project.mvc")
	public String searchProject(Model m,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("fNameInput");
		Connection c;
		try {
			c = dataSource.getConnection();	
			log.info(name);
			c = dataSource.getConnection();
			Statement s = c.createStatement();
			String sqlString = "SELECT name FROM project WHERE name LIKE '%"+name+"%' ORDER BY 1;";
			ResultSet rs = s.executeQuery(sqlString);			      
				      if(rs.next()) {
				    	  System.out.println("GOINGIN HERE");
				    	  m.addAttribute("projects", worldMapper.searchProjects(name));
				    		return "removeProjectSearch";  
				      } else {
				    	  m.addAttribute("errorMessages", "No results found, verify name");
				      }
				          
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		m.addAttribute("removeEmployee", worldMapper.getEmployeeByName(name));
		return "removeEmployee";
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
@RequestMapping("/updateProject.mvc")
public String gotoUpdateP(Model m){
	return "updateProjectbefore";
}

@RequestMapping("/acUpdate_employee.mvc")
public String acUpdateEmployee(Model m, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String ID=request.getParameter("empId");
	long temp = Long.parseLong(ID);
	m.addAttribute("employee", worldMapper.updateEmployee(temp));
	return "acUpdateEmployees";
}
@RequestMapping("/update_project.mvc")
public String acUpdateProject(Model m, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String ID=request.getParameter("pID");
	long temp = Long.parseLong(ID);
	m.addAttribute("project", worldMapper.updateProject(temp));
	return "updateProject";
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
@RequestMapping(value = "/doUpdateProject.mvc")
protected String doNewUpdateProject(Model m,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String n0=request.getParameter("pIdInput");
			String n1=request.getParameter("pNameInput");
			String n2=request.getParameter("startDateInput");
			String n3=request.getParameter("endDateInput");
			System.out.println(n2 + n3);
			Connection c;
			try {
				
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
					//java.util.Date dob = null;
					java.util.Date startDate = null;
					java.util.Date endDate = null;
					java.sql.Date sqlDate = null;
					java.sql.Date sqlDate2 = null;

					try {
						startDate = formatter.parse(n2);
						endDate = formatter.parse(n3);
						sqlDate = new java.sql.Date(startDate.getTime());
						sqlDate2 = new java.sql.Date(endDate.getTime());
						System.out.println(sqlDate);
						c = dataSource.getConnection();
						Statement s = c.createStatement();
			
			String sqlString = "CALL updateProject("+n0+", \""+n1+"\",'"+sqlDate+"','"+sqlDate2+"');";
			System.out.println(sqlString);
			s.executeUpdate(sqlString);
		return "manageprojects.mvc";
		}catch(ParseException e){
			return "#";
		}	
			}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "#";
		} 
			
}

@RequestMapping(value = "/newProject.mvc")
protected String doNewProject(Model m,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String n1=request.getParameter("pNameInput");
		String n2=request.getParameter("startDateInput");
		String n3=request.getParameter("endDateInput");
		System.out.println(n2 + n3);
		Connection c;
		try {
			
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				//java.util.Date dob = null;
				java.util.Date startDate = null;
				java.util.Date endDate = null;
				java.sql.Date sqlDate = null;
				java.sql.Date sqlDate2 = null;

				try {
					startDate = formatter.parse(n2);
					endDate = formatter.parse(n3);
					sqlDate = new java.sql.Date(startDate.getTime());
					sqlDate2 = new java.sql.Date(endDate.getTime());
		
					c = dataSource.getConnection();
					Statement s = c.createStatement();
					String sqlString = "CALL addProject(\""+n1+"\",'"+sqlDate+"','"+sqlDate2+"');";
					System.out.println(sqlString);
					s.executeUpdate(sqlString);
				return "user created";
				} catch (ParseException e) {
					return "error";	
				}
			
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
					return "homeFinanceEmployee";
				}  else if (category.equals("Chris")){
					return "chrisPage";
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

@RequestMapping("/home_finance_employee.mvc")
public String homeFinanceEmpoyee(Model m){
	return "homeFinanceEmployee";
}

@RequestMapping("/view_pay_roll.mvc")
public String viewPayroll(Model m){
	System.out.println(worldMapper.getPayroll());
	m.addAttribute("payrolls", worldMapper.getPayroll());
	return "viewPayroll";
}

@RequestMapping("/profits.mvc")
public String viewProfits(Model m){
	return "viewProfits";
}

@RequestMapping("/assing_employee.mvc")
public String assignEmployee(Model m){
	return "assignEmployee";
}

@RequestMapping("/assign_employee_controller.mvc")
public String assignEmployeeToProject(Model m,HttpServletRequest request, HttpServletResponse response){
	
	String proID=request.getParameter("empID");
	String empID=request.getParameter("proID");
	String startDate =request.getParameter("startDate");
	String endDate  = request.getParameter("endDate");
	
	startDate = startDate.replaceAll("/", "-");
	endDate =   endDate.replaceAll("/", "-");
	
	log.info(startDate);
	log.info(endDate);

	
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	
	java.util.Date startDateF = null;
	java.util.Date endDateF = null;
	java.sql.Date  sqlDate = null;
	java.sql.Date  sqlDate2 = null;
	Connection c;
	try {
		
		c = dataSource.getConnection();
		Statement s = c.createStatement();
		
		startDateF = formatter.parse(startDate);
		endDateF = formatter.parse(endDate);
		
		sqlDate = new java.sql.Date(startDateF.getTime());
		sqlDate2 = new java.sql.Date(endDateF.getTime());
		
		String sqlString = "CALL assignToProject("+empID+ ","+proID+",'"+sqlDate+"','"+sqlDate2+"');";
		log.info(sqlString);
		int test = s.executeUpdate(sqlString);
		
		if(test == 1) {
			log.info("worked");
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return "assignEmployee";
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
