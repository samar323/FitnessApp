<%@page import="dao.DAO"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	HashMap userDetails =(HashMap)session.getAttribute("userDetails");
	if(userDetails!=null){
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
	<link href="https://fonts.googleapis.com/css2?family=Baloo+Bhai+2:wght@800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
	<style>
		 body {
            font-family: 'Baloo Bhai 2', cursive;
            margin: 0px;
            padding: 0px;
            top: 20px;
            background: url('img/bg.jpg');
            color: black;
        }
		.container{
			display: inline;
		}
		.container img{
			display: inline;
			margin: auto;
			border-radius: 90px;
		}
	</style>
	<meta charset="ISO-8859-1">
	<title>Fitness</title>
</head>

<body>
	<h1>Fitness</h1>
	<hr>
	Welcome:<b><%=userDetails.get("name") %></b>
	<a href='Logout'>Logout</a>
	<a href='UserFollowTrainer.jsp'>Your Trainer</a>
	<a href='UserReport.jsp'>Your Report</a>
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
	<div class="container">
		<div id="profile">
			<img src="GetPhoto?email=<%=userDetails.get("email") %>&type=user" height="100px" width="100px" />
		</div>
		<div class="user-details">
			<div class="details">
				Email: <b><%=userDetails.get("email") %></b><br>
			</div>
			<div class="details">
				Phone: <b><%=userDetails.get("phone") %></b><br>
			</div>
			<div class="details">
				Age: <b><%=userDetails.get("age") %></b><br>
			</div>
			<div class="details">
				<%
				String address=(String)userDetails.get("address");
				if(!address.trim().equals("")){
				%>
				Address: <b><%=userDetails.get("address") %></b><br>
				<% 
				}
				%>
			</div>

		</div>
	</div>
	<hr>
	<form action="SearchTrainer.jsp" method="post">
		Name: <input type="text" name="name" /> <br><br>
		Category: <select name='category'>
			<option>Yoga</option>
			<option>Cardio</option>
			<option>Jumba</option>
		</select><br><br>
		<button type="submit">Search Trainer</button>
	</form>
	<hr>
	
</body>

</html>
<%
	}else{
		session.setAttribute("message","Plz LOGIN First!");
		response.sendRedirect("index.jsp");
	}
%>