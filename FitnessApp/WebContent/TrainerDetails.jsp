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
	<form action="SearchTrainer.jsp" method="post">
		Name: <input type="text" name="name" />
		Category: <select name='category'>
			<option>Yoga</option>
			<option>Cardio</option>
			<option>Jumba</option>
		</select>
		<button type="submit">search Trainer</button>
	</form>
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
	<br>
	<%
		String email=request.getParameter("email");
		DAO dao=new DAO();
		HashMap trainer=dao.getTrainerByEmail(email);
		
	%>
	<img src='GetPhoto?email=<%=trainer.get("email") %>&type=trainer' height="100px" width="100px"/><br>
		Name: <%=trainer.get("name") %>,
		Phone: <%=trainer.get("phone") %>,
		Email: <%=trainer.get("email") %>,
		Gender: <%=trainer.get("gender") %>,
		DOB: <%=trainer.get("dob") %>,
		<br>
		Certificate 1:<a href='DownloadFile?email=<%=trainer.get("email")%>&certificate=1&fname=<%=trainer.get("fileName1") %>'><%=trainer.get("fileName1") %></a>
		<%
		String fileName2=(String)trainer.get("fileName2");
		if(fileName2!=null){
		%>
			Certificate 2: <a href='DownloadFile?email=<%=trainer.get("email")%>&certificate=2&fname=<%=trainer.get("fileName2") %>'><%=trainer.get("fileName2") %></a>
		<%
		}
		%>
		Experience: <%=trainer.get("exp") %>,
		Description: <%=trainer.get("description") %>
		<hr>
		<%
		HashMap schedule=dao.getTrainerScheduleByEmail(email);
		%>
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
			boolean result=dao.checkFollow((String)userDetails.get("email"), email);
			if(result!=true){
		%>
		<form action="FollowTrainer" method="post">
			<input type="hidden" name="trainer_email" value="<%=email%>"/>
			<button type="submit">Follow this schedule</button>
		</form>
		<%}else{
			%>
			<h2 style="color:green">You are following this trainer</h2>
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