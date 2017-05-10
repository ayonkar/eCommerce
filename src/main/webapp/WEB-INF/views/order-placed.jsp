<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
 <%  Date date = new Date(); %>  
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script> -->
<link href="<c:url value="/resources/css/orderPlaced.css" />" rel="stylesheet">  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<h3 align = "center">Order successfully placed</h3>
 <c:choose>
       <c:when test="${empty requestScope.order}">
            <h3>No Orders Found!</h3>                
       </c:when>
               
       <c:otherwise>
	<form:form action="${contextPath}/orderPlaced" method="post">
		        
		        <table border="1" align = "center">
                <tr>
                	<th align = "center">OrderId</th>
                    <th align = "center">OrderDate</th>
                    <th align = "center">ProductId</th>
                    <th align = "center">Product Name</th>
                    <th align = "center">Price</th> 
                    <th align = "center">Shipped Status</th>              
                </tr>
             <c:forEach items="${requestScope.order}" var="orderList">
                 <tr>
                     <td> ${orderList.orderId}</td>
			         <td> ${orderList.orderDate}</td>
			         <td> ${orderList.product.productId}</td>
			         <td> ${orderList.product.productName}</td>
			         <td> ${orderList.product.price}</td>
			         <td> ${orderList.shipStatus}</td>       
                </tr>
            </c:forEach>
            </table></br>



	</form:form>
     </c:otherwise>
</c:choose>

<!--<button class = "button" align = "center">Under Construction</button>
 <script>
$(document).ready(function(){
    $("button").click(function(){
        $.ajax({type : "POST", url: "demo_test.htm", success: function(result){
            $("#div1").html(result);
        }});
    });
});
</script> -->
		
</body>
</html>