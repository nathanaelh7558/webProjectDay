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
   <li><a href='adminpage.mvc'><span>Home</span></a></li>
   <li><a href='employees.mvc'><span>View Employees</span></a></li>
   <li><a href='manageemployees.mvc'><span>Add Employee</span></a></li>
   <li><a href='remove_employee_page.mvc'><span>Remove Employee</span></a></li>
   <li><a href='update_employee_details.mvc'><span>Update Details</span></a></li>
   <li><a href='assing_employee.mvc'><span>Assign employee</span></a></li>
</ul>

</div>

<div id=container>
<form name="newEmployeeForm" action="assign_employee_controller.mvc" method="POST">
	<section>
	<h3>Assign employee to project</h3	>
	  <ul class="input-list style-1 clearfix">
	  	<li><input type="text" name="empID" placeholder="Employee ID"></li>
	  	<li><input type="text" name="proID" placeholder="Project ID"></li>
	    <li><input type="date" name="startDate" placeholder="Start date"></li>
	  	<li><input type="date" name="endDate" placeholder="End date"></li>
	  	<input type="submit" value="Assign employee">
	  </ul>
	</section>
</form>

</container>

 </body>