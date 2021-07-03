<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	HashMap userDetails =(HashMap)session.getAttribute("userDetails");
	HashMap adminDetails =(HashMap)session.getAttribute("adminDetails");
	HashMap trainerDetails =(HashMap)session.getAttribute("trainerDetails");
	
	if(userDetails!=null){
		response.sendRedirect("UserHome.jsp");
	}else if(adminDetails!=null){
		response.sendRedirect("AdminHome.jsp?status=pending");
	}else if(trainerDetails!=null){
		response.sendRedirect("TrainerHome.jsp");
	}else{
%>
<%
response.setHeader("Pragma","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Expires","0");
response.setDateHeader("Expires",-1);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fitness</title>
</head>
<body>
<h1>Fitness</h1>
	<hr>
	<a href='AdminLogin.jsp'>AdminLogin</a>
	<a href='TrainerLogin.jsp'>TrainerLogin</a>
	<%
		String m=(String)session.getAttribute("message");
		if(m!=null){
	%>
			<p style="background-color: yellow;width:max-content;"><%=m %></p>
	<%		
			session.setAttribute("message",null);
		}
	%>
	<h1>Login Form</h1>
	<form action="UserLogin" method="post">
		Email: <input type="email" name="email" /> <br>
		<br> Password: <input type="password" name="password" /> <br>
		<br>
		<button type="submit">Login</button>
		<button type="reset">Reset</button>
	</form>
	<hr>
	<h1>Resgistration Form</h1>
	<form action="UserRegister" method="post" enctype="multipart/form-data">
		Email: <input type="email" name="email" required/> <br><br> 
		Password: <input type="password" name="password" required/> <br><br>
		Name: <input type="text" name="name" required/> <br><br> 
		Phone: <input type="text" name="phone" required/> <br><br>
		Gender: <input type="radio" name="gender" value="Male" checked/>Male
				<input type="radio" name="gender" value="Female" />Female <br><br> 
		Age: <input type="number" name="age" required/> <br><br>
		Address: <textarea rows="3" name="address"></textarea> <br><br> 
		Photo: <input type="file" name="photo" /> <br><br>
		<button type="submit">Register</button>
		<button type="reset">Reset</button>
	</form>
</body>
</html><%
	}
%>