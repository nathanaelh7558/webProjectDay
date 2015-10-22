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
	return "continents";
}
@RequestMapping("/{cont}/regions.mvc")
public String regions(Model m, @PathVariable("cont") String continent){
	m.addAttribute("regions", worldMapper.getRegionsForContinent(continent));
	m.addAttribute("continent", continent);
	return "regions";
}
@RequestMapping("/{region}/countries.mvc")
public String countries(Model m, @PathVariable String region){
	m.addAttribute("countries", worldMapper.getCountriesForRegion(region));
	m.addAttribute("region", region);
	return "countries";
}
@RequestMapping("/{CountryCode}/cities.mvc")
public String cities(Model m, @PathVariable String CountryCode){
	m.addAttribute("cities", worldMapper.getCitiesForCountry(CountryCode));
	m.addAttribute("CountryCode", CountryCode);
	return "cities";
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
					return "employees.mvc";
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
