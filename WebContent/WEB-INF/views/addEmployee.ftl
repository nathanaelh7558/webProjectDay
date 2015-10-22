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
<form name="newEmployeeForm" action="newEmployee.mvc" method="POST">
<h1>Add a new Employee!</h1>

Title: <input type="text" name="titleInput" placeholder="Title">
First Name: <input type="text" name="fNameInput" placeholder="First Name">
Last Name: <input type="text" name="lNameInput" placeholder="Last Name">
DOB: <input type="date" name="dobInput" placeholder="DOB">
Salary: <input type="number" name="salaryInput" placeholder="Salary">
<input type="submit" value="Submit">

</form>


</body>
</html>