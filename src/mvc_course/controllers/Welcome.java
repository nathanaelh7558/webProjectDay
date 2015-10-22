package mvc_course.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Welcome {

	@Autowired
	private DataSource dataSource;
	public Welcome() {
		// TODO Auto-generated constructor stub
	}
	@RequestMapping(value="index.mvc")
	public String landingPage(Model m){
		m.addAttribute("msg", "Hello from the controller!");
		return "hello";
	}
	@RequestMapping(value="CityList.mvc")
	public String cityList(Model m){
		try {
			Connection c = dataSource.getConnection();
			Statement s = c.createStatement();
			String sqlString = "SELECT name, district, population FROM City WHERE countrycode = 'GBR';";
			ResultSet rs = s.executeQuery(sqlString);
			List<String[]> rows = new ArrayList<String[]>();
			
			while(rs.next()){
				String[] row= {
						rs.getString(1),
						rs.getString(2),
						rs.getString(3) };
				rows.add(row);
				}
			m.addAttribute("rows", rows);
			} catch (Exception e) {
				System.out.println(e.getMessage());
		}
		return "CityListx";
	}

}
