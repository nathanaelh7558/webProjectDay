<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Cities in ${CountryCode}</h1>
<table border=1>
<tr><th>City</th><th>District</th></tr>
<#list cities as city>
<tr>
<td>${city.name}</td><td>${city.district}</td>
</tr>
</#list>
</table>
</body>
</html>