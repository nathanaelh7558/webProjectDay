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
<title>Employees</title>
</head>
<body>
<div id='cssmenu'>
<ul>
   <li><a href='adminpage2.mvc'><span>Home</span></a></li>
   <li><a href='employees.mvc'><span>View Employees</span></a></li>
   <li><a href='manageemployees.mvc'><span>Add Employee</span></a></li>
   <li><a href='#'><span>Remove Employee</span></a></li>
   <li><a href='#'><span>Update Details</span></a></li>
</ul>
</div>
<h1>Employees</h1>
<table class = "pure-table pure-table-bordered">
<thead>
<tr><th>Employee Id</th><th>Employee First Name</th><th>Employee Last Name</th><th>Employee Salary</th></tr>
</thead>
<#list employees as employee>
<tbody>
<tr>
<td>${employee.employeeId}</td><td>${employee.fname}</td><td>${employee.lname}</td><td>${employee.salary}</td>
</tr>
</tbody?
</#list>
</table>
</body>
</html>