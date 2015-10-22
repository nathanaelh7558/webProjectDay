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
   <li><a href='adminpage2.mvc'><span>Home</span></a></li>
   <li><a href='projects.mvc'><span>View Projects</span></a></li>
   <li><a href='addProject.mvc'><span>Add Project</span></a></li>
   <li><a href='#'><span>Remove Project</span></a></li>
   <li><a href='#'><span>Update Project</span></a></li>
</ul>

</div>
   
   <div id=container>
<form name="newProjectForm" action="newProject.mvc" method="POST">
	<section>
	<h3>Add a new Employee</h3	>
	  <ul class="input-list style-1 clearfix">
	  	<li><input type="text" name="pNameInput" placeholder="project name"></li>
	  	<li><input type="Date" name="startDateInput" placeholder="Start Date(DD-MM-YYYY)"></li>
	  	<li><input type="Date" name="EndDateInput" placeholder="End Date(DD-MM-YYYY)"></li>	  	
	  	<input type="submit" value="Create Project">
	  </ul>
	</section>
</form>
</container>

</body>
</html>