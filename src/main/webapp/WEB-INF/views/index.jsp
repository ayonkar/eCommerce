<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>eCommerce Application</title>
</head>
<body>


	<h1><center>DaddysKart</center></h1>
	
	<form action="user/login" method="post">
	
	<h2>Sign in</h2>
		<table>
		<tr>
		    <td><b>Username:</b></td>
		    <td><input name="username" size="30" required="required" placeholder = "Username"/></td>
		</tr>
		
		<tr>
		    <td><b>Password:</b></td>
		    <td><input type="password" name="password" size="31" required="required" placeholder = "Password"/></td>
		</tr>
		
		<tr>
		    <td colspan="2"><input type="submit" value="Sign in" /></td>
		</tr>
				
		</table>
		
		<h5>New to DaddysKart?</h5>
		
		<a href="user/register.htm"><center>Create your DaddysKart account</center></a><br/>
	</form>


</body>
</html>

