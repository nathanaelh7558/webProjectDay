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
   <li><a href='home_finance_employee.mvc'><span>Home</span></a></li>
   <li><a href='view_pay_roll.mvc'><span>View Pay roll</span></a></li>
    <li><a href='profits.mvc'><span>View Profits</span></a></li>
</ul>
 
</div>

<h1>Pay roll</h1>
<table class = "pure-table pure-table-bordered">
<thead>
<tr><th>Employee Id</th><th>Employee Name</th><th>Employee Amount Due</th></tr>
</thead>
<#list payrolls as payroll>
<tbody>
<tr>
<td>${payroll.employeeId}</td><td>${payroll.employeeName}</td><td>${payroll.totalAmountDue}</td>
</tr>
</tbody>
</#list>
</table>
</body>
</html>