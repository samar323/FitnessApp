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
	<%
	String name=request.getParameter("name");
	String category=request.getParameter("category");
	%>
	<hr>
	<form action="SearchTrainer.jsp" method="post">
		Name: <input type="text" name="name" />
		Category: <select name='category'>
			<option><%=category%></option>
			<option>Yoga</option>
			<option>Cardio</option>
			<option>Jumba</option>
		</select>
		<button type="submit">search Trainer</button>
	</form>
	<hr><br>
	<%
		
		DAO dao=new DAO();
		ArrayList<HashMap> trainers=dao.getTrainersByNameCategory(name,category);
		for(HashMap trainer:trainers){
	%>
		Name: <%=trainer.get("name") %>,
		Phone: <%=trainer.get("phone") %>,
		Email: <%=trainer.get("email") %>,
		Gender: <%=trainer.get("gender") %>,
		Category: <%=trainer.get("category") %>,
		Experience: <%=trainer.get("exp") %><br>
		<a href='TrainerDetails.jsp?email=<%=trainer.get("email") %>'>More Details</a>
		<hr>
	<%
		}
	%>

<%
	}else{
		session.setAttribute("message","Plz LOGIN First!");
		response.sendRedirect("index.jsp");
	}
%>
</body>

</html>