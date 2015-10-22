<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FreeMarker Student List</title>
</head>
<body>
List generated at ${.now}
<ul>
<#list studentList as s>
<#if s.gender="MALE">
<#assign c="blue">
<#else>
<#assign c="red">
</#if>
<li style="color: ${c}">${s.firstName} ${s.lastName} ${s.university}
</#list>
</ul>
</body>
</html>