package mvc_data;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
public interface IWorldMapper {
	String GET_PERSON_BY_ID = "SELECT employeeId,dob,fname, lname, title, salary FROM employee WHERE fName = #{personId, jdbcType=VARCHAR} ORDER BY 1;";
	String GET_PROJECT_BY_ID = "SELECT projectId,name,startDate, endDate FROM project WHERE name = #{personId, jdbcType=VARCHAR} ORDER BY 1;";
	String GET_P_BY_ID = "SELECT projectId,name,startDate, endDate FROM project WHERE projectId = #{ID};";

	String GET_EMP_BY_ID = "SELECT employeeId,dob,fname, lname, title, salary FROM employee WHERE employeeId = #{ID};";

@Select("SELECT employeeId,dob,fname, lname, title, salary FROM employee ORDER BY 1;")
List<Employee> getEmployees();

@Select(GET_PERSON_BY_ID)
List<Employee> searchEmployees(String personId);

@Select(GET_PROJECT_BY_ID)
List<Project> searchProjects(String personId);

@Select(GET_EMP_BY_ID)
Employee updateEmployee(long ID);
@Select(GET_P_BY_ID)
Project updateProject(long ID);

@Select("SELECT projectId, name, startDate, endDate FROM project ORDER BY 1;")
List<Project> getProjects();

@Select("SELECT DISTINCT Region, Continent " + "FROM Country WHERE Continent =#{continent}" + "ORDER BY 1")
List<String> getRegionsForContinent(@Param("continent") String continent);

@Select("SELECT Code, Name, Region, Continent " + "FROM Country WHERE region=#{region} "+"ORDER BY 2" )
List<Country> getCountriesForRegion(@Param("region") String region);

@Select("SELECT CountryCode, Name, District " + "FROM City WHERE CountryCode=#{CountryCode} "+"ORDER BY 2" )
List<City> getCitiesForCountry(@Param("CountryCode") String CountryCode);

@Select("SELECT employeeId, fName FROM employee where fName=#{fName};")
List<Employee> getEmployeeByName(String fName);
}
