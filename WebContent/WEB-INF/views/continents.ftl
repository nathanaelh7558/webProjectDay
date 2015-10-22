<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Employees</h1>
<table border=1>
<tr><th>Employee Id</th><th>Employee First Name</th><th>Employee Last Name</th><th>Employee Salary</th></tr>
<#list employees as employee>
<tr>
<td>${employee.employeeId}</td><td>${employee.fname}</td><td>${employee.lname}</td><td>${employee.salary}</td>
</tr>
</#list>
</table>
</body>
</html>