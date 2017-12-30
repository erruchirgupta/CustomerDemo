<title>Customers Search</title>
<body>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
	<div class="container">
		<div class="well well-sm">
			<h2>Search Customer By ID:</h2>
		</div>

		<form:form method="post" action="searchCustomer" id="searchCustomer"
			cssClass="form-horizontal" modelAttribute="customer">

			<%-- 		<form class="form-horizontal" name="myForm"
			action="${pageContext.request.contextPath}/ProcessServlet"
			method="post" onSubmit="return validateForm()"> --%>


			<div class="form-group">
				<label class="control-label col-sm-2" for="user">Customer ID
					:</label>
				<div class="col-sm-4">
					<form:input path="cid" cssClass="form-control" />
				</div>
				<%-- <span class="label label-danger">${errorMsg}</span> --%>
			</div>
			<c:if test="${errorMsg ne '' }">
<%-- 			<c:if test="${errorMsg eq 'is not a Valid Customer ID, try again' or errorMsg eq 'Enter a Valid Customer ID!' }"> --%>
				<div class="alert alert-danger col-md-4 col-md-offset-2"
					align="center">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Error!</strong> ${customer.cid} ${errorMsg}
				</div>
			</c:if>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-info">
				<span class="glyphicon glyphicon-search"></span> Search
			</button>
			<!-- <button type="submit" class="btn btn-default">Search</button> -->
		</div>
	</div>
	</form:form>
	</div>

</body>