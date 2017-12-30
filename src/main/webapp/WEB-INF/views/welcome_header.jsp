<%@page import="com.demo.beans.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>

<c:if test="${user eq ''}">
<c:redirect url="${pageContext.request.contextPath}/" />
</c:if>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/home">ABC Bank Ltd</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="${pageContext.request.contextPath}/home">Home</a></li>

		
			<c:if test="${user ne ''}">

				<li><a
					href="${pageContext.request.contextPath}/activeCustomersPage">Active
						Customers</a></li>
				<li><a
					href="${pageContext.request.contextPath}/inActiveCustomersPage">InActive
						Customers</a></li>
				<li><a
					href="${pageContext.request.contextPath}/addCustomersPage">Add
						Customers</a></li>
				<li><a
					href="${pageContext.request.contextPath}/searchCustomersPage">Search
						Customers</a></li>

			</c:if>
		</ul>
		<ul class="nav navbar-nav navbar-right">

			<c:if test="${user ne ''}">
				<li><a href="${pageContext.request.contextPath}/home"><span
						class="glyphicon glyphicon-user"></span> ${user} </a>
				<li><a
					href="${pageContext.request.contextPath}/logout"><span
						class="glyphicon glyphicon-log-in"></span> Log out </a></li>
			</c:if>
		</ul>
	</div>
</nav>
</head>