<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanUser"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulari P2</title>
<link rel="stylesheet" type="text/css" href="css/estil.css" />
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/form_validator.js"></script>

</head>
<body>
	<%
		BeanUser user = null;
		if (request.getAttribute("user") != null) {
			user = (BeanUser) request.getAttribute("user");
		} else { 	
			user = new BeanUser();
		}
	%>

	<form action="/Practica2/formcontroller" method="POST" id=registerForm>
		<div id="form-content">
			<fieldset>

				<div class="fieldgroup">
					<label for="fullName">Nom i cognom:</label> <input type="text"
						name="fullName" value="<%=user.getFullName()%>" id="fullName" />
					<%
						if (user.getError()[0] == 1) {
					%>
					<label class="error">Introdueix el teu nom</label>
					<%
						}
					%>
				</div>

				<div class="fieldgroup">
					<label for="username">Nom d'usuari:</label> <input type="text"
						name="username" value="<%=user.getUsername()%>" id="username" />
						<span id="usernameAjaxError" class="ajax-error"></span>
					<%
						if (user.getError()[1] == 1) {
					%>
					<label class="error">Aquest compte d' usuari ja està sent utilitzat</label>
					<%
						}
					%>
				</div>

				<div class="fieldgroup">
					<label for="mail">Correu electrònic:</label> <input type="text"
						name="mail" value="<%=user.getMail()%>" id="mail" />
						<span id="mailAjaxError" class="ajax-error"></span>
					<%
						if (user.getError()[2] == 1) {
					%>
					<label class="error">Aquest compte de correu ja està sent utilitzat</label>
					<%
						}
					%>
				</div>

				<div class="fieldgroup">
					<label for="pwd">Contrasenya:</label> <input type="password"
						name="pwd" value="<%=user.getMail()%>" id="pwd" />
					<%
						if (user.getError()[3] == 1) {
					%>
					<label class="error">Introdueix una contraseya</label>
					<%
						}
					%>
				</div>

				<div class="fieldgroup">
					<label for="pwd_check">Repeteix la contrasenya:</label> <input
						type="password" name="pwd_check" value="<%=user.getMail()%>"
						id="pwd_check" />
					<%
						if (user.getError()[4] == 1) {
					%>
					<label class="error">Les contrasenyes no concordent</label>
					<%
						}
					%>
				</div>
				<div class="fieldgroup">
					<input type="submit" value="Enviar" class="submit" />
				</div>


			</fieldset>
		</div>
	</form>
	<p>Twittsire rules!</p>
	<br>
	<hr align="center" width="95%">
	<h2>DB login results:</h2>
	<div id="results">
		<%@ include file="/loginresults.jsp"%>
	</div>
</body>
</html>


