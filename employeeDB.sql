DROP DATABASE IF EXISTS EmpDB;

CREATE DATABASE EmpDB;

USE EmpDB;


CREATE TABLE employee (  
	employeeId int(11) PRIMARY KEY AUTO_INCREMENT, 
	dob datetime, 
	fName varchar(30), 
	lName varchar(30), 
	title varchar(20), 
	picture blob, 
	salary decimal(30,2) 
);
CREATE TABLE salesEmp (  
	employeeId int(11), 
	comissionRate decimal(5,2), 
	totalAnnualRevenue decimal(10,2), 
	FOREIGN KEY (employeeId) 
	REFERENCES employee(employeeId)
ON DELETE 
	CASCADE
);
CREATE TABLE billableEmp ( 
	employeeId int(11), 
	dayRate decimal(8,2), 
	CV blob,
 	FOREIGN KEY (employeeId) 
	REFERENCES employee(employeeId) 
ON DELETE 
	CASCADE
);
CREATE TABLE project ( 
	projectId int(11) PRIMARY KEY AUTO_INCREMENT, 
	name varchar(30), 
	startDate datetime, 
	endDate datetime 
);
CREATE TABLE project_staff ( 
	employeeId int(11), 
	projectId int(11),
	startDate datetime, 
	endDate datetime,
	FOREIGN KEY (employeeId) 
	REFERENCES billableEmp(employeeId) 
ON DELETE 
	CASCADE, 
	FOREIGN KEY (projectId) 
	REFERENCES project(projectId) 
ON DELETE 
	CASCADE
);

CREATE TABLE credentials ( 
ID int PRIMARY KEY AUTO_INCREMENT, 
username varchar(30) UNIQUE, 
password varchar(30), 
name varchar(50), 
category ENUM('Admin', 'Finance', 'Chris') NOT NULL 
);

DELIMITER // 
CREATE PROCEDURE fillTables ()
BEGIN
-- Project data
INSERT INTO project VALUES('','Project1','2015-01-01','2017-12-10');
INSERT INTO project VALUES('','Project2','2016-01-01','2016-12-10');
INSERT INTO project VALUES('','Project3','2017-01-01','2017-12-10');
-- Employee data
INSERT INTO employee VALUES('','1995-04-14','Employee','One','Mr','','15000');
INSERT INTO employee VALUES('','1995-05-14','Employee','Two','Mrs','','15000');
INSERT INTO employee VALUES('','1995-06-14','Employee','Three','Master','','15000');
INSERT INTO employee VALUES('','1995-07-14','Employee','Four','Miss','','15000');
INSERT INTO employee VALUES('','1995-08-14','Employee','Five','Mr','','15000');
INSERT INTO employee VALUES('','1995-09-14','Employee','Six','Mr','','15000');
INSERT INTO billableEmp VALUES('1','10','');
INSERT INTO billableEmp VALUES('2','5','');
INSERT INTO billableEmp VALUES('3','2.5','');
INSERT INTO billableEmp VALUES('4','100','');
INSERT INTO billableEmp VALUES('5','25','');
INSERT INTO billableEmp VALUES('6','15','');
-- Project-Staff data
INSERT INTO project_staff VALUES('1','1','2015-01-02','2015-12-09');
INSERT INTO project_staff VALUES('2','1','2016-04-02','2015-06-09');
INSERT INTO project_staff VALUES('3','2','2016-04-02','2016-12-09');
INSERT INTO project_staff VALUES('4','2','2016-11-02','2016-11-09');
INSERT INTO project_staff VALUES('5','3','2017-01-02','2017-03-09');
INSERT INTO project_staff VALUES('6','3','2017-01-02','2017-12-09');
-- Creds data
INSERT INTO credentials VALUES('','IAMCHRIS','password','Chris Reid','Chris');
INSERT INTO credentials VALUES('','financeUser','password','Sandra Smith','Finance');
INSERT INTO credentials VALUES('','jordanho','password','Jordan Hoey','Admin');
INSERT INTO credentials VALUES('','edulima','password','Eduardo Lima','Admin');
INSERT INTO credentials VALUES('','nathanaelh7558','password','Nathanael Holmes','Admin');


end //
DELIMITER ;

CALL fillTables();

-- Insert Normal Employee Method

DELIMITER // 
CREATE PROCEDURE insertEmployee (
	newDob datetime, newFName varchar(30), newLName varchar(30), 
	newTitle varchar(20), newSalary decimal(11,2)
	)
BEGIN
INSERT INTO employee(dob, fName, lName, title, salary)
VALUES (newDob, newFName, newLName, newTitle, newSalary);
end //
DELIMITER ;

-- Insert Sales Employee Method

DELIMITER // 
CREATE PROCEDURE insertSalesEmployee (
	ID int(11), CR decimal(5,2), 
	TR decimal(10,2)
	)
BEGIN
INSERT INTO salesEmp(employeeId, commissionRate, totalAnnualRevenue)
VALUES (ID, CR, TR);
end //
DELIMITER ;

-- Insert into Billable Employee

DELIMITER // 
CREATE PROCEDURE insertBillableEmployee (
	ID int(11), DR decimal(8,2), 
	CVO blob 
	)
BEGIN
INSERT INTO billableEmp(employeeId, dayRate, CV)
VALUES (ID, DR, CVO);
end //
DELIMITER ;

-- Remove Employee

DELIMITER //
CREATE PROCEDURE removeEmployee(
	ID int(11)	
	)
BEGIN 
DELETE FROM employee 
WHERE employee.employeeId = ID;
END //
DELIMITER ;

-- Update Employee

DELIMITER // 
CREATE Procedure updateEmployee(
	ID int(11),
	newDOB datetime,
	newFName varchar(30),
	newLName varchar(30),
	newTitle varchar(30),
	newSalary decimal(11,2)	
	)
BEGIN
UPDATE 
	employee
SET 
	dob = newDOB,
	fName = newFName,
	lName = newLName,
	title = newTitle,
	salary = newSalary
WHERE 
	employeeId = ID;
end //
DELIMITER ; 

-- Add a Project 

DELIMITER // 
CREATE PROCEDURE addProject ( 
	Pname varchar(30), 
	Sdate datetime, 
	Edate datetime 
	)
BEGIN
INSERT INTO project(name, startDate, endDate )
VALUES (Pname, Sdate, Edate);
end //
DELIMITER ;

-- Update Project

DELIMITER // 
CREATE Procedure updateProject(
	ID int(11),
	newName varchar(30),
	newStartDate datetime,
	newEndDate datetime	
	)
BEGIN
UPDATE 
	project
SET 
	name = newName,
	startDate = newStartDate,
	endDate = newEndDate
WHERE 
	projectId = ID;
end //
DELIMITER ; 
-- Remove Project

DELIMITER //
CREATE PROCEDURE removeProject(
	ID int(11)	
	)
BEGIN 
DELETE FROM project 
WHERE projectId = ID;
END //
DELIMITER ;

-- Assign billable employee
-- Admin team ONLY 

DELIMITER // 
CREATE PROCEDURE assignToProject (
	EID int(11),
	PID int(11),
	Sdate datetime, 
	Edate datetime 
	)
BEGIN
if(EID in(SELECT employeeId from billableEmp)) THEN
SET @tempStart := (SELECT startDate FROM project WHERE projectId =  PID);
SET @tempEnd := (SELECT endDate FROM project WHERE projectId = PID);
IF ((Sdate between @tempStart and @tempEnd) AND 
(Edate between @tempStart and @tempEnd)) THEN
INSERT INTO project_staff(employeeId, projectId, startDate, 
	endDate)
VALUES (EID, PID, Sdate, Edate);
ELSE SELECT 'Error, these are not valid dates for this project', @tempStart,@tempEnd, Sdate, Edate;
END IF;
ELSE SELECT 'Not a billable employee';
END IF;
end //
DELIMITER ;

-- Return employee matches from name input

DELIMITER // 
CREATE 
PROCEDURE listEmployees (searchName varchar(30))
BEGIN
SELECT 
employeeId, CONCAT(title, '.',fName, ' ',lName) AS 'Employee Name' 
FROM 
employee
WHERE 
fName LIKE ('%searchName%');
end //
DELIMITER ;

-- List projects

DELIMITER // 
CREATE PROCEDURE showProjects ()
BEGIN
SELECT projectId, name AS 'Project Name' FROM project;
end //
DELIMITER ;

-- List Sales Employees with user input

DELIMITER // 
CREATE 
PROCEDURE showSalesEmployees (searchName varchar(30))
BEGIN
SELECT 
employee.employeeId, CONCAT(title, '.',fName, ' ',lName) AS 'Sales Employee Name', 
CONCAT(ROUND(commission_rate,2),'%') AS 'Commission Rate' 
FROM employee 
JOIN salesEmployee 
	ON employee.employeeId = salesEmployee.employeeId 
WHERE employee.fName LIKE ('%searchName%');
end //
DELIMITER ;

-- List Billable Employees

DELIMITER // 
CREATE 
PROCEDURE showBillableEmployees (searchName varchar(30))
BEGIN
SELECT employee.employee_id, CONCAT(Title, '.',F_name, ' ',L_name) 
AS 'Billable Employee Name', 
CONCAT(ROUND(day_rate,2),'%') AS 'Day Rate' 
FROM employee 
JOIN employee_billable 
	ON employee.employee_id = employee_billable.employee_id 
WHERE employee.title LIKE ('%searchName%');
end //
DELIMITER ;

-- Payroll

DELIMITER //
CREATE PROCEDURE payRoll () 
BEGIN 
SELECT @temp := employee.employeeId AS 'EmployeeId', 
concat(fName, ' ', lName) AS 'EmployeeName', 
if(@temp IN(SELECT employeeId FROM salesEmp), 
(SELECT CONCAT('£',ROUND(Salary+((totalAnnualRevenue/100)*comissionRate)))
from employee JOIN salesEmp ON employee.employeeId = salesEmp.employeeId ), 
CONCAT('£',ROUND(employee.salary))) AS 'TotalAmountDue'
FROM employee; 
END //
DELIMITER ;

DELIMITER // 
CREATE PROCEDURE showEmployeesWithoutProjects()
BEGIN
	SELECT employee.employee_id, employee.DateOfBirth,
		employee.F_name, employee.L_name, employee.Title, employee.Picture, employee.Salary
	FROM employee LEFT JOIN project_staff
	ON employee.employee_id = project_staff.employee_id
	WHERE project_staff.project_id IS NULL;
end //
DELIMITER ;

DELIMITER // 
CREATE PROCEDURE showProjectsWithoutEmployees()
BEGIN
	SELECT project.project_id, project.name, project.start_date, project.end_date
	FROM project LEFT JOIN project_staff
	ON project.project_id = project_staff.project_id
	WHERE project_staff.project_id IS NULL;
end //
DELIMITER ;

