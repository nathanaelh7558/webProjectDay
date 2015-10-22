package mvc_data;

import java.sql.Blob;
import java.util.Date;

public class Employee {
	private String employeeId;
	private Date dob;
	private String fname;
	private String lname;
	private String title;
	private Float Salary;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Float getSalary() {
		return Salary;
	}

	public void setSalary(Float salary) {
		Salary = salary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

}
