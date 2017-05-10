<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <link href="<c:url value="/resources/css/userHome.css" />" rel="stylesheet">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>User Home</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<form:form action="${contextPath}user/home" method="GET">

		<!-- <h4>Welcome ${user.firstName}!</h4> -->
		
	<div class = "header">
			<div class = header-level2>
				<c:if test="${user.firstName != null}">
					Welcome ${user.firstName}!
						<a href="${pageContext.request.contextPath}/home" style = "color:black">
						<b>Logout</b>
						</a>	
				</c:if>
			<c:if test="${user.firstName == null}">
					<a href="${pageContext.request.contextPath}/login" style = "color:black">
						<b>Login</b>
					</a>
			</c:if>
		</div>
	</div></br> 		
</form:form>

<form:form action="${contextPath}user/home1" method = "POST">

<div class="menu">
  <ul>
   <li><a href="${pageContext.request.contextPath}/home2" style = "color:white">Home</a></li>
   <li class = "dropdown"><a href="javascript:void(0)" class = "btn">Product List</a>
   		<div class = "content">
   			<a href="${pageContext.request.contextPath}/productList1" style = "color:white">Electronics</a>
   			<a href="${pageContext.request.contextPath}/productList2" style = "color:white">Books</a>
   			<a href="${pageContext.request.contextPath}/productList3" style = "color:white">Apparels</a>
   		</div>
   </li>
   <li><a href="${pageContext.request.contextPath}/orders" style = "color:white">My Orders</a></li>
   <li><a href="${pageContext.request.contextPath}/shoppingCart" style = "color:white">Cart</a></li>
	   		
  </ul> 
 </div> 
</form:form>	
</body></html>

<div id="container">
     &nbsp;&nbsp;&nbsp;&nbsp;<img src="./resources/images/banner.jpg" style="width:1305px;height:500px;"> 
</div>


</body>
</html>	


