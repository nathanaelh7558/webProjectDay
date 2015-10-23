<html>
<head>
    <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
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
<form name="newEmployeeForm" action="newUpdateEmployee.mvc" method="POST">
	<section>
	<h3>Update Employee</h3	>
	  <ul class="input-list style-1 clearfix">
	  <li><input type="text" name="empIdInput" placeholder="Title" value = ${employee.employeeId}></li>
	  	<li><input type="text" name="titleInput" placeholder="Title" value = ${employee.title}></li>
	  	<li><input type="text" name="fNameInput" placeholder="First Name" value = ${employee.fname}></li>
	  	<li><input type="text" name="lNameInput" placeholder="Last Name" value = ${employee.lname}></li>
	  	<li><input type="date" name="dobInput" placeholder="Date of Birth (DD-MM-YYYY)" value = ${employee.dob?datetime}></li> 	
	  	<li><input type="number" name="salaryInput" placeholder="Salary" value = ${employee.salary?string.number}></li>
	  	<input type="submit" value="Update">
	  </ul>
	</section>
</form>

</container>

 </body>