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
		String trainer_email=dao.getFollowTrainerEmail((String)userDetails.get("email"));
		if(trainer_email!=null){
		HashMap trainerDetails=dao.getTrainerByEmail(trainer_email);
		HashMap schedule=dao.getTrainerScheduleByEmail(trainer_email);
	%>
	<%
		String m=(String)session.getAttribute("message");
		if(m!=null){
	%>
			<p style="background-color: yellow;width:max-content;"><%=m %></p>
	<%		
			session.setAttribute("message",null);
		}
	%>
		<form action="UnFollowTrainer" method="post">
			<input type="hidden" name="trainer_email" value="<%=trainer_email%>"/>
			<button type="submit">UnFollow this schedule</button>
		</form>
		<h2 style="color:green">Trainer Details</h2>
		Name: <b><%=trainerDetails.get("name") %></b><br>
		Exercise 1: <%=schedule.get("ex1") %>
		Exercise 2: <%=schedule.get("ex2") %>
		Exercise 3: <%=schedule.get("ex3") %>
		Exercise 4: <%=schedule.get("ex4") %>
		<br>
		Meal 1: <%=schedule.get("meal1") %>
		Meal 2: <%=schedule.get("meal2") %>
		Meal 3: <%=schedule.get("meal3") %>
		Meal 4: <%=schedule.get("meal4") %>
		<hr>
		
		<%
			boolean status=dao.checkCurrentDateRecord((String)userDetails.get("email"));
			if(status==false){
		%>
		<form action="AddUserScheduleRecord" method="post">
			Exercise 1:<input type="checkbox" name="ex1"/><br><br>
			Exercise 2:<input type="checkbox" name="ex2"/><br><br>
			Exercise 3:<input type="checkbox" name="ex3"/><br><br>
			Exercise 4:<input type="checkbox" name="ex4"/><br><br>
			Meal 1:<input type="checkbox" name="meal1"/><br><br>
			Meal 2:<input type="checkbox" name="meal2"/><br><br>
			Meal 3:<input type="checkbox" name="meal3"/><br><br>
			Meal 4:<input type="checkbox" name="meal4"/><br><br>
			<input type="submit" value="Submit">
		</form>
		<% }else{%>
		<h3 style="color:green">Today's Task uploaded.</h3>
		<% }%>
	<%
		}else{
	%>
		<h2 style="color:red">You are not following any trainer.</h2>
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