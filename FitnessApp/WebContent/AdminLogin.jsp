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
	<h1>Admin Login Form</h1>
	<form action="AdminLogin" method="post">
		Admin ID: <input type="test" name="aid" /> <br>
		<br> Password: <input type="password" name="password" /> <br>
		<br>
		<button type="submit">Login</button>
	</form>
	<hr>

</body>
</html>
<%
	}
%>