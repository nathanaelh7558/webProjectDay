<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Continents</h1>
<table border=1>
<tr><th>Continent</th></tr>
<#list continents as continent>
<tr>
<td><a href='${rc.contextPath}/${continent}/regions.mvc'>${continent}</a></td>
</tr>
</#list>
</table>
</body>
</html>