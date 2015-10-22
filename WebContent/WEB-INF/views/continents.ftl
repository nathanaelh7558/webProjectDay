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
<h1>Projects</h1>
<table border=1>
<tr><th>Project Id</th><th>Project Name</th><th>Project Start Date</th><th>Project End Date</th></tr>
<#list projects as project>
<tr>
<td>${project.projectId}</td><td>${project.name}</td><td>${project.startDate}</td><td>${project.endDate}</td>
</tr>
</#list>
</table>
</body>
</html>