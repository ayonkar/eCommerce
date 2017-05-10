<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/registerUser.css" />" rel="stylesheet">
<title>Add User Form</title>
</head>
<body>


	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}" style = "text-decoration:none"><h4>&#8592;</h4></a><br/>

	<form:form action="${contextPath}/user/register" commandName="user"
		method="post">
		
	 <h2>&nbsp;Create account</h2>	

		<table>
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" size="30" required="required" placeholder = "First name"/>
					<font color="red"><form:errors path="firstName" /></font></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" size="30" required="required" placeholder = "Last name"/>
					<font color="red"><form:errors path="lastName" /></font></td>
			</tr>


			<tr>
				<td>User Name:</td>
				<td><form:input path="username" size="30" required="required" placeholder = "Username"/>
					<font color="red"><form:errors path="username" /></font></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="31"
						required="required" placeholder = "Password"/> <font color="red"><form:errors
							path="password" /></font></td>
			</tr>

			<tr>
				<td>Email Id:</td>
				<td><form:input path="email.emailAddress" size="30"
						type="email" required="required" placeholder = "Email id" /> <font color="red"><form:errors
							path="email.emailAddress" /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Create your Amazon account" /></td> 

			</tr>
		</table>
		</br>

	</form:form>



</body>
</html>