<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
   <link href="<c:url value="/resources/css/createProduct.css" />" rel="stylesheet"> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>User Home</title>
</head>
	
<body>

	 <c:set var="contextPath" value="${pageContext.request.contextPath}" />	
	  <form:form action = "${contextPath}/updateProduct" commandName = "product" method = "post">
		<h2 align = "center">Product Description</h2>	

		<table>
			<tr>
				<td >Category Name:</td>
				<td>
					<select name = "dropdown">
					  <option value="electronics">Electronics</option>
					  <option value="books">Books</option>
					  <option value="apparels">Apparels</option>
					</select>
				</td>
			<tr>
				<td>Product Name:</td>
				<td><input name ="productName" size="30" required="required" placeholder = "Product Name"/>
					<font color="red"><form:errors path="productName" /></font></td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><input name="price" size="30" required="required" placeholder = "Price"/>
					<font color="red"><form:errors path="price" /></font></td>
			</tr>
			
			<input name="productId" value="${productId}" type="hidden">
				<td colspan="2"><input type="submit" value="Update product" /></td> 

			</tr>
		</table>
		</br> 
	
	
	</form:form>
</body>
</html>

