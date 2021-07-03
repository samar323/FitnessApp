<%@page import="dao.DAO"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	HashMap trainerDetails =(HashMap)session.getAttribute("trainerDetails");
	if(trainerDetails!=null){
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
	<%
		String m=(String)session.getAttribute("message");
		if(m!=null){
	%>
			<p style="background-color: yellow;width:max-content;"><%=m %></p>
	<%		
			session.setAttribute("message",null);
		}
	%>
	<hr>
	Welcome:
	<b><%=trainerDetails.get("name")%></b>
	<h2>[Status: <%=trainerDetails.get("status") %>]</h2>
	<a href='Logout'>Logout</a>
	<hr>

	<img alt="" src="GetPhoto?email=<%=trainerDetails.get("email")%>&type=trainer"
		height="100px" width="100px" /> <br>
		Email:<b><%=trainerDetails.get("email")%></b>
	<br> Phone: <b><%=trainerDetails.get("phone")%></b>
	<br> DOB:<b><%=trainerDetails.get("dob")%></b>
	<br> Address:<b><%=trainerDetails.get("address")%></b>
	<br> Category:<b><%=trainerDetails.get("category")%></b>
	<br> Gender:<b><%=trainerDetails.get("gender")%></b>
	<br>
	<hr>
	<h2>Certificates:</h2>
	Certificate 1:<a href='DownloadFile?email=<%=trainerDetails.get("email")%>&certificate=1&fname=<%=trainerDetails.get("fileName1") %>'><%=trainerDetails.get("fileName1") %></a>
		<%
		String fileName2=(String)trainerDetails.get("fileName2");
		if(fileName2!=null){
		%>
			Certificate 2: <a href='DownloadFile?email=<%=trainerDetails.get("email")%>&certificate=2&fname=<%=trainerDetails.get("fileName2") %>'><%=trainerDetails.get("fileName2") %></a>
		<%
		}
		%>
		<hr>
		<br>
		<%
			String status=(String)trainerDetails.get("status");
			DAO dao=new DAO();
			HashMap schedule=dao.getTrainerScheduleByEmail((String)trainerDetails.get("email"));
			if(status.equalsIgnoreCase("Accept") && schedule==null){
		%>
		<h1>Upload Your Schedule:</h1>
		<form action="AddSchedule" method="post" enctype="multipart/form-data">
			Exercise-1: <input type="text" name="ex1" required/> <br><br> 
			Exercise-2: <input type="text" name="ex2" required/> <br><br> 
			Exercise-3: <input type="text" name="ex3" required/> <br><br> 
			Exercise-4: <input type="text" name="ex4" required/> <br><br> 
			Meal-1: <input type="text" name="meal1" required/> <br><br> 
			Meal-2: <input type="text" name="meal2" required/> <br><br> 
			Meal-3: <input type="text" name="meal3" required/> <br><br> 
			Meal-4: <input type="text" name="meal4" required/> <br><br> 
			<button type="submit">Add Schedule</button>
			<button type="reset">Reset</button>
		</form>
		
		<%
		}else{
			%>
				<h2>Your Schedule:</h2>
				Exercise 1: <%=schedule.get("ex1") %>
				Exercise 2: <%=schedule.get("ex2") %>
				Exercise 3: <%=schedule.get("ex3") %>
				Exercise 4: <%=schedule.get("ex4") %>
				<br>
				Meal 1: <%=schedule.get("meal1") %>
				Meal 2: <%=schedule.get("meal2") %>
				Meal 3: <%=schedule.get("meal3") %>
				Meal 4: <%=schedule.get("meal4") %>
			<%
		}
		%>
</body>

</html>
<%
	}else{
		session.setAttribute("message","Plz LOGIN First!");
		response.sendRedirect("TrainerLogin.jsp");
	}
%>