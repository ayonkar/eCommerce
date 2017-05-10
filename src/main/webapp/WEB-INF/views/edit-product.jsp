<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link href="<c:url value="/resources/css/edit-product.css" />" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Products</title>
</head>
<body> 
<h1>Please select the product you want to update</h1>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<form:form action="${contextPath}/product/update" commandName="product" method="post">

	<select name="productName">
            <c:forEach var="list" items="${productList}">
         <option><c:out value="${list.productName}"/></option>
     	
            </c:forEach>
     </select>
     </br></br>
     <input type="submit" name = "action" class = "button" value="Update Product">
     
 </form:form> 
 </br>  
</body>
</html>

