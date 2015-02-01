<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanUser"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista usuarios</title>
<link rel="stylesheet" type="text/css" href="css/estil.css" />
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
</head>
<body>
	<%
		BeanUser[] users = BeanUser.getUsers();
	%>
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Surname</th>
				<th>Username</th>
				<th>Mail</th>
				<th>Password</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (int i = 0; i < users.length; ++i) {
					out.println("<tr>");
					out.println("<td>" + users[i].getName() + "</td>");
					out.println("<td>" + users[i].getSurname() + "</td>");
					out.println("<td>" + users[i].getUsername() + "</td>");
					out.println("<td>" + users[i].getMail() + "</td>");
					out.println("<td>" + users[i].getPwd() + "</td>");
					out.println("</tr>");
				}
			%>
		</tbody>
	</table>
</body>
</html>