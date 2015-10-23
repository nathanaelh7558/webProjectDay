package mvc_data;

public class payRoll {
private String EmployeeId;
private String EmployeeName;
private String TotalAmountDue;
	public payRoll() {
		// TODO Auto-generated constructor stub
	}
	public String getTotalAmountDue() {
		return TotalAmountDue;
	}
	public void setTotalAmountDue(String totalAmountDue) {
		TotalAmountDue = totalAmountDue;
	}
	public String getEmployeeId() {
		return EmployeeId;
	}
	public void setEmployeeId(String employeeId) {
		EmployeeId = employeeId;
	}
	public String getEmployeeName() {
		return EmployeeName;
	}
	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

}
