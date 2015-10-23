<!DOCTYPE html> 
<html>
<head>
    <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
         	<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
    <link href="css/style.css" rel="stylesheet" type="text/css" />   
   <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
   <script src="script.js"></script>
    <title>MVC Authentication System</title> 
</head>
<body>

<div id='cssmenu'>
<ul>
   <li><a href='adminpage2.mvc'><span>Home</span></a></li>
   <li><a href='employees.mvc'><span>View Employees</span></a></li>
   <li><a href='addemployeepage.mvc'><span>Add Employee</span></a></li>
   <li><a href='remove_employee_page.mvc'><span>Remove Employee</span></a></li>
   <li><a href='update_employee_details.mvc'><span>Update Details</span></a></li>
</ul>

</div>

<div id=container>
<form name="newEmployeeForm" action="acUpdate_employee.mvc" method="POST">
	<section>
	<h3>Update Employee</h3	>
	  <ul class="input-list style-1 clearfix">
	  	<li><input type="text" name="empId" placeholder="Type employee's id to be updated"></li>
	  	<input type="submit" value="Search Employee">
	  </ul>
	</section>
</form>
	<table class = "pure-table pure-table-bordered">
<thead>
<tr><th>Employee Id</th><th>Employee First Name</th><th>Employee Last Name</th><th>Employee Salary</th></tr>
</thead>
<#list employees as employee>
<tbody>
<tr>
<td>${employee.employeeId}</td><td>${employee.fname}</td><td>${employee.lname}</td><td>${employee.salary}</td>
</tr>
</tbody>
</#list>
</table>

</body>
</html>