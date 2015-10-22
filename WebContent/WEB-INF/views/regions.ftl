<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Regions for ${continent}</h1>
<table border=1>
<tr><th>Regions</th></tr>
<#list regions as region>
<tr>
<td><a href='${rc.contextPath}/${region}/countries.mvc'>${region}</a></td>
</tr>
</#list>
</table>
</body>
</html>