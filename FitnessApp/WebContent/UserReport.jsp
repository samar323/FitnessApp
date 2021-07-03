<%@page import="java.util.ArrayList"%>
<%@page import="dao.DAO"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	HashMap userDetails =(HashMap)session.getAttribute("userDetails");
	if(userDetails!=null){
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
	Welcome:<b><%=userDetails.get("name") %></b>
	<a href='Logout'>Logout</a>
	<a href='UserHome.jsp'>Home</a>
	<hr>
	<%
		DAO dao=new DAO();
	ArrayList<HashMap> allRecords=dao.getUserRecord((String)userDetails.get("email"));
	for(HashMap records:allRecords){
	%>	Date: <%=records.get("record_date") %><br>
		Exercise 1: <%=records.get("ex1") %>
		Exercise 2: <%=records.get("ex2") %>
		Exercise 3: <%=records.get("ex3") %>
		Exercise 4: <%=records.get("ex4") %>
		<br>
		Meal 1: <%=records.get("meal1") %>
		Meal 2: <%=records.get("meal2") %>
		Meal 3: <%=records.get("meal3") %>
		Meal 4: <%=records.get("meal4") %>
		<br>
		Exercise rating:<%=records.get("ex_rating") %>
		Meal rating:<%=records.get("meal_rating") %>
		<hr>
	<%
	}
	%>  
</body>

</html>
<%
	}else{
		session.setAttribute("message","Plz LOGIN First!");
		response.sendRedirect("index.jsp");
	}
%>