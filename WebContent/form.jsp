<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanUser" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exemple Formulari </title>
<link rel="stylesheet" type="text/css" href="css/estil.css" />
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script>
$(document).ready(function(){
    $("#registerForm").validate();
  });
</script>
</head>
<body>

<% 
BeanUser user = null;
if (request.getAttribute("user")!=null) {
	user = (BeanUser)request.getAttribute("user");
}
else {
	user = new BeanUser();
}
%>

<form action="/epaw_p2/formcontroller" method="post" id=registerForm>
<table>
<tr> 
<td> User id </td> 
<td> <input type="text" name="user" value="<%=user.getUser() %>" id="user" class="required" minlength="5"/> </td> 
<% 	
	if ( user.getError()[0] == 1) {
		%> 
		<td class="error"> The username already exists in our DB! </td>  
		<% 
	}
%>
</tr>
<tr> 
<td> Mail </td> <td> <input type="text" name="mail"  value = "<%=user.getMail() %>" id="mail" class="required email"/> </td>
 </tr>
<tr> 
<td> <input type="submit" value="Enviar"> </td>
</tr>
</table>
</form>
</body>
</html>