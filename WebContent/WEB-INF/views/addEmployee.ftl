<!DOCTYPE html> 
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
   <li><a href='addemployeepage.mvc'><span>Add Employee</span></a></li>
   <li><a href='remove_employee_page.mvc'><span>Remove Employee</span></a></li>
   <li><a href='update_employee_details.mvc'><span>Update Details</span></a></li>
</ul>

</div>

<div id=container>
<form name="newEmployeeForm" action="addemployee.mvc" method="POST">
	<section>
	<h3>Add a new Employee</h3	>
	  <ul class="input-list style-1 clearfix">
	  	<li><input type="text" name="titleInput" placeholder="Title"></li>
	  	<li><input type="text" name="fNameInput" placeholder="First Name"></li>
	  	<li><input type="text" name="lNameInput" placeholder="Last Name"></li>
	  	<li><input type="date" name="dobInput" value="Date of birth"></li> 	
	  	<li><input type="number" name="salaryInput" placeholder="Salary"></li>
	  	<input type="submit" value="Add Employee">
	  </ul>
	</section>
</form>


</body>
</html>