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
   <li><a href='removeProject.mvc'><span>Remove Project</span></a></li>
   <li><a href='updateProject.mvc'><span>Update Project</span></a></li>
</ul>

</div>
   
   <div id=container>
<form name="newProjectForm" action="doUpdateProject.mvc" method="POST">
	<section>
	<h3>Update Project</h3	>
	  <ul class="input-list style-1 clearfix">
	  <li><input type="text" name="pIdInput" placeholder="project Id" value=${project.projectId}></li>
	  	<li><input type="text" name="pNameInput" placeholder="project name" value=${project.name}></li>
	  	<li><input type="text" name="startDateInput" placeholder="Start Date(DD-MM-YYYY)" value=${project.startDate?date}></li>
	  	<li><input type="text" name="endDateInput" placeholder="End Date(DD-MM-YYYY)"value=${project.endDate?date}></li>	  	
	  	<input type="submit" value="Update Project">
	  </ul>
	</section>
</form>
</container>

</body>
</html>