<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<title>User Registration</title>
<body>
	<div class="container">
		<div class="well well-sm">
			<h3>User Registration form</h3>
		</div>
		<form:form method="post" cssClass="form-horizontal" action="addUser" id="addUser" modelAttribute="users">
		<%-- <form class="form-horizontal" name="myForm"
			action="${pageContext.request.contextPath}/ProcessServlet"
			method="post" > --%>
			<%-- onSubmit="return validateForm()">--%>

		
			<div class="form-group">
				<label class="control-label col-sm-2" for="fname"> Full
					Name :</label>
				<div class="col-sm-4">
				<form:input path="name" cssClass="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="lname"> Password
					:</label>
				<div class="col-sm-4">
					<form:input path="pass" cssClass="form-control"/> 
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class="btn btn-default">Reset</button>
				</div>
			</div>
	 </form:form>	<%-- </form> --%>
	</div>
</body>
</html>