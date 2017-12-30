<%@page import="com.demo.beans.*"%>

<head>
<!--<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</head> -->
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/">ABC Bank Ltd</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="${pageContext.request.contextPath}/home">Home</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">

			<li><a
				href="${pageContext.request.contextPath}/registerUserPage"><span
					class="glyphicon glyphicon-user"></span> Sign up</a>
			<li><a href="${pageContext.request.contextPath}/"><span
					class="glyphicon glyphicon-log-in"></span> Log in </a></li>
		</ul>
	</div>
	</nav>
</head>