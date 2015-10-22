package mvc_data;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface IWorldMapper {
@Select("SELECT employeeId,dob,fname, lname, title, salary FROM employee ORDER BY 1;")
List<Employee> getEmployees();
@Select("SELECT projectId, name,startDate, endDate FROM project ORDER BY 1;")
List<Project> getProjects();
@Select("SELECT DISTINCT Region, Continent " + "FROM Country WHERE Continent =#{continent}" + "ORDER BY 1")
List<String> getRegionsForContinent(@Param("continent") String continent);
@Select("SELECT Code, Name, Region, Continent " + "FROM Country WHERE region=#{region} "+"ORDER BY 2" )
List<Country> getCountriesForRegion(@Param("region") String region);
@Select("SELECT CountryCode, Name, District " + "FROM City WHERE CountryCode=#{CountryCode} "+"ORDER BY 2" )
List<City> getCitiesForCountry(@Param("CountryCode") String CountryCode);
}
