<%@page import="dao.DAO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	HashMap adminDetails =(HashMap)session.getAttribute("adminDetails");
	if(adminDetails!=null){
		String status=request.getParameter("status");
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
	<h1>Fitness [Admin Page]</h1>
	<hr>
	Welcome: <b><%=adminDetails.get("name") %></b>
	<a href='Logout'>Logout</a>
	<hr>
	<a href='AdminHome.jsp?status=pending'>Pending Trainers</a>
	<a href='AdminHome.jsp?status=accept'>Accept Trainers</a>
	<a href='AdminHome.jsp?status=reject'>Reject Trainers</a><br>
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
	<%
	DAO dao=new DAO();
	ArrayList<HashMap> allTrainers=dao.getAllTrainer(status);
	for(HashMap trainer:allTrainers){
	%>
		<img src='GetPhoto?email=<%=trainer.get("email") %>&type=trainer' height="100px" width="100px"/><br>
		Name: <%=trainer.get("name") %>
		Phone: <%=trainer.get("phone") %>
		Email: <%=trainer.get("email") %>
		Gender: <%=trainer.get("gender") %>
		DOB: <%=trainer.get("dob") %>
		<br>
		<%
			String s=(String)trainer.get("status");
			if(s.equalsIgnoreCase("pending")){
		%>
			<a href="ChangeTrainerStatus?email=<%=trainer.get("email") %>&status=Accept">Accept</a>
			<a href="ChangeTrainerStatus?email=<%=trainer.get("email") %>&status=Reject">Reject</a>
		<%
			}else if(s.equalsIgnoreCase("accept")){
		%>
			<a href="ChangeTrainerStatus?email=<%=trainer.get("email") %>&status=Reject">Reject</a>
		<%
			}else if(s.equalsIgnoreCase("reject")){
		%>
			<a href="ChangeTrainerStatus?email=<%=trainer.get("email") %>&status=Accept">Accept</a>
		<%
			}
		%>
		<br>Status: <%=s %>
		<a href='DownloadFile?email=<%=trainer.get("email")%>&certificate=1&fname=<%=trainer.get("fileName1") %>'><%=trainer.get("fileName1") %></a>
		<%
		String fileName2=(String)trainer.get("fileName2");
		if(fileName2!=null){
		%>
		<a href='DownloadFile?email=<%=trainer.get("email")%>&certificate=2&fname=<%=trainer.get("fileName2") %>'><%=trainer.get("fileName2") %></a>
		<%
		}
		%>
		<hr>
	<%
	}
	%>
	
</body>

</html>
<%
	}else{
		session.setAttribute("message","Plz LOGIN First!");
		response.sendRedirect("AdminLogin.jsp");
	}
%>