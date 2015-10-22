package mvc_course.controllers;

import mvc_data.IWorldMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WorldController {
@Autowired
public IWorldMapper worldMapper;

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
	public WorldController() {
		// TODO Auto-generated constructor stub
	}

}
