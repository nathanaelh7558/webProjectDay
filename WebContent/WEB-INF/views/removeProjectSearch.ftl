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
   <li><a href='#'><span>Update Project</span></a></li>
</ul>

</div>

<div id=container>
<form name="newEmployeeForm" action="delete_project.mvc" method="POST">
	<section>
	<h3>Remove Project</h3	>
	  <ul class="input-list style-1 clearfix">
	  	<li><input type="text" name="pID" placeholder="Enter project ID"></li>
	  	<input type="submit" value="Delete Project">
	  </ul>
	</section>
</form>
<table class = "pure-table pure-table-bordered">
<thead>
<tr><th>Project Id</th><th>Project Name</th><th>Project Start Date</th><th>Project End Date</th></tr>
</thead>
<#list projects as project>
<tbody>
<tr>
<td>${project.projectId}</td><td>${project.name}</td><td>${project.startDate?date}</td><td>${project.endDate?date}</td>
</tr>
</tbody>
</#list>
</table>
	
</body>

</html>