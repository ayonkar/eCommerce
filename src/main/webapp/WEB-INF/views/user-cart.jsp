<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/resources/css/userCart.css" />" rel="stylesheet"> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<h3 align = "center">Cart items</h3>
 
	<form:form action="${contextPath}/checkout" method="post">
		        
		        <table border="1" align = "center">
                <tr>
                    <th align = "center">Product Name</th>
                    <th align = "center">Price</th>
                    <th align = "center">Action</th>
                </tr>
                
               <tr> 
               		<c:forEach var="list" items="${product}">
                     <td><input type = "text" name = "productName" value ="${list.productName}" readonly></td>
			         <td> ${list.price}</td>
			         <td><a href="${pageContext.request.contextPath}/removeFromCart?name=${list.productName}" style = "color:black">Remove</a></td>		 

            </tr>
            </c:forEach>
            </table>
       <div style="text-align:center"> 
        <input type="submit" class = "button" value="Place Order">
       </div> 
	</form:form>
     
 

</body>
</html>