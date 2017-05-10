<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link href="<c:url value="/resources/css/added-products.css" />" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<h3 align = "center">List of products</h3>
 <c:choose>
       <c:when test="${empty requestScope.productList}">
            <h3>No Products Found!</h3>                
       </c:when>
               
       <c:otherwise>
	<form:form action="${contextPath}/product/edit" method="post">
		        
		   <table border="1" align = "center">
                <tr>
                    <th align = "center">Product Name</th>
                    <th align = "center">Price</th>
                    
                </tr>
             <c:forEach items="${requestScope.productList}" var="product">
                 <tr>
                     <td> ${product.productName}</td>
			         <!--  <td> ${product.productImage}</td> -->
			         <td> ${product.price}</td>
			 		 <!-- <td><a href="${pageContext.request.contextPath}/removeFromTable?name=${list.productName}" style = "color:black">Remove</a></td> -->	
        		</tr>
        	</c:forEach>
         </table></br>
        <div style="text-align:center"> 
        <input type="submit" value="Edit Product" class = "button" align = "center">
        </div>
	</form:form>
     
        </c:otherwise>
       
       </c:choose>


</body>
</html>