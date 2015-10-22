<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%!
Connection dbCon;
	ResultSet rs;
%>
<%
	Class.forName("com.mysql.jdbc.Driver");
	dbCon = DriverManager.getConnection("jdbc:mysql://localhost/world", "root", "ch@ngeme1");
	//Connection Created
	Statement stmt;
	String sqlString = "SELECT name, district, population FROM City WHERE countrycode = 'GBR' ORDER BY population DESC;";
	stmt = dbCon.createStatement();
	rs = stmt.executeQuery(sqlString);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>City List</title>
</head>
<body>
	<h1>City List</h1>
	<%
		if (rs != null) {
	%>
	<table border="1">
		<%
			//print heading row
				out.print("<tr>");
				//print headers
				ResultSetMetaData md = rs.getMetaData();
				for (int c = 1; c <= md.getColumnCount(); c++) {
					out.print("<th>" + md.getColumnName(c) + "</th>");
				}
				out.println("<tr>");
				//print following rows
				while (rs.next()) {
					out.println("<tr>");
					for (int c = 1; c <= md.getColumnCount(); c++) {
						out.print("<td>" + rs.getString(c) + "</td>");
					}
					out.println("</tr>");
				}
		%>

	</table>
	<%
		} //end check for null resultset
	%>
</body>
</html>