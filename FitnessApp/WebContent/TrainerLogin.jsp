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
	<%
		String m=(String)session.getAttribute("message");
		if(m!=null){
	%>
			<p style="background-color: yellow;width:max-content;"><%=m %></p>
	<%		
			session.setAttribute("message",null);
		}
	%>
	<h1>Trainer Login Form</h1>
	<form action="TrainerLogin" method="post">
		Email: <input type="email" name="email" /> <br>
		<br> Password: <input type="password" name="password" /> <br>
		<br>
		<button type="submit">Login</button>
	</form>
	<hr>
	<h1>Resgistration Form</h1>
	<form action="TrainerRegister" method="post" enctype="multipart/form-data">
		Email: <input type="email" name="email" required/> <br><br> 
		Password: <input type="password" name="password" required/> <br><br>
		Name: <input type="text" name="name" required/> <br><br> 
		Phone: <input type="text" name="phone" required/> <br><br>
		Gender: <input type="radio" name="gender" value="Male" checked/>Male
				<input type="radio" name="gender" value="Female" />Female <br><br> 
		DOB: <input type="date" name="dob" required/> <br><br>
		Address: <textarea rows="3" name="address"></textarea> <br><br> 
		Photo: <input type="file" name="photo" required/> <br><br>
		Category: <select name='category'>
			<option>Yoga</option>
			<option>Cardio</option>
			<option>Jumba</option>
		</select><br><br>
		
		Aadhaar Number: <input type="text" name="aadhaar" required/> <br><br>
		Certificate 1: <input type="file" name="certificate1" required/> <br><br>
		Certificate 2: <input type="file" name="certificate2" /> <br><br>
		Experience: <select name='exp'>
			<option>0</option>
			<option>1+ year</option>
			<option>2+ year</option>
			<option>3+ year</option>
			<option>4+ year</option>
			<option>5+ year</option>
		</select><br><br>
		Description: <textarea rows="3" name="description" required></textarea> <br><br> 
		<button type="submit">Register</button>
		<button type="reset">Reset</button>
	</form>
</body>
</html>
<%
	}
%>