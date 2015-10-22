<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addProject</title>
</head>
<body>

<form name="newProjectForm" action="newProject.mvc" method="POST">
<h1>Add a new Project!</h1>

Project Name: <input type="text" name="pNameInput" placeholder="project name">
Start Date: <input type="Date" name="startDateInput" placeholder="DD-MM-YYYY">
End Date: <input type="Date" name="EndDateInput" placeholder="DD-MM-YYYY">

<input type="submit" value="Submit">

</form>

</body>
</html>