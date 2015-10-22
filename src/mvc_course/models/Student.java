package mvc_course.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Student {
public static Set<Student> list = new HashSet<Student>();
private String firstName;
private String lastName;
private String gender;
private String university;

	public Student() {
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
