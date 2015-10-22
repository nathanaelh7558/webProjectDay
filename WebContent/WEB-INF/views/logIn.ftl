<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">

body {
 background-color: #94d1f0;
}
.body {
	width:60%;
	margin-left: auto;
	margin-right: auto;
	font-family: 'Roboto', sans-serif;	
	height: 550px;
	margin-top: 70px;
	
}



form {
width: 80%;
margin-left:auto;
margin-right:auto;
}

h1 {
font-size: 2.3em;
font-weight: 400;
color: #fff;


}

h2 {
margin-top: 30px;
font-weight: 400;
color: #fff;
margin-bottom: 30px;
font-size: 2em;
}

p {
margin-bottom: 40px;
color: #fff;

}

input {
padding: 3px;
}

.inputField {
margin-top: 20px;
}

input[type=text] {
padding:10px; 
border:2px solid #fff; 
border-radius: 5px;
width: 100%;

}
input[type=text]:focus {
border-color:#018dbb; 
}

input[type=submit] {
margin-left: -2px;
margin-top: 30px;
padding:5px 15px;
 background:#018dbb; 
 color: #fff;
 border:0 none;
cursor:pointer;
-webkit-border-radius: 5px;
border-radius: 5px;
width: 100%;
height: 40px; 
}

a {
color: #fff
 }
</style>
<body>
<div class="body">



<form name="user" action="hello.mvc" method="post">
<h1>
	MVC Authentication System Form  
</h1>

<h2>Login</h2>
<p>Insert the fields below to login</p>
<div class="inputField">
  <input type="text" name="usernameInput" placeholder="Username">
  <br />
  </div>
  <div class="inputField">
  <input type="text" name="password" placeholder="Password">
  <br />
  
  </div>
  
  <div class="inputField">
  <a href="home">Register a new user</a>
  </div>
  
  <div class="button">
  <input type="Submit" value = "Submit" />
  </div>
</form>
</div>
</body>
</html>