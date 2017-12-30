<title>Update Customer details</title>
<body>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix ="form" %>
	
	<div class="container">
		<div class="well well-sm">
			<h3>Update Customer Details : ${customer.cid}   </h3><h3>              Added by: ${customer.teller} </h3>
		</div>
				<form:form method="post" cssClass="form-horizontal" action="${pageContext.request.contextPath}/updateCustomer" id="updateCustomer" modelAttribute="customer">
<%-- 			<form class="form-horizontal" name="myForm"
			action="${pageContext.request.contextPath}/ProcessServlet" method="post"
			> --%>
			<!-- onSubmit="return validateForm()">-->
			
			
			<form:hidden  path="cid" cssClass="form-control"/>
			<form:hidden  path="teller" cssClass="form-control"/>
			<form:hidden  path="status" cssClass="form-control"/>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="fname">
					First Name :</label>
				<div class="col-sm-4">
					<form:input  path="fname" cssClass="form-control"/>

				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="lname">
					Last Name :</label>
				<div class="col-sm-4">
					<form:input path="lname" cssClass="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="email"> Email id
					:</label>
				<div class="col-sm-4">
					<form:input path="email" cssClass="form-control"/>
				</div>
			</div>
			
						<div class="form-group">
				<label class="control-label col-sm-2" for="dob"> Date of Birth
					:</label>
				<div class="col-sm-4">
				<form:input path="dob" type="date" cssClass="form-control" min="1980-01-01"
				pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|
			2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|
			1[02])-31))" />
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="ctype">
					Type :</label>
				<div class="col-sm-4">
<%-- 					<form:input path="ctype" cssClass="form-control"/> --%>
				<form:select cssClass="dropdowns" path="ctype">
					<form:option value="General" label="General"/>
   					<form:option value="Minor" label="Minor"/>
   					<form:option value="Senior Citizen" label="Senior Citizen"/>
				</form:select>
				
				</div>
			</div>
			
			
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="phoneno">Phone no.
					:</label>
				<div class="col-sm-4">
									<form:input path="phoneno" cssClass="form-control"/>

				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="address">Address
					:</label>
				<div class="col-sm-4">
					<form:input path="address" cssClass="form-control"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="city">City
					:</label>
				<div class="col-sm-4">
					<form:input path="city" cssClass="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="state">State
					:</label>
				<div class="col-sm-4">
					<form:input path="state" cssClass="form-control"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="zip">Zip
					:</label>
				<div class="col-sm-4">
					<form:input path="zip" cssClass="form-control"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="pan">PAN
					:</label>
				<div class="col-sm-4">
					<form:input path="pan" cssClass="form-control"/>
				</div>
			</div>
			
				<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button name=button value="submit" type="submit" class="btn btn-default">Submit</button>
					<button name=button value ="cancel" type="cancel" class="btn btn-default">Cancel</button>
				</div>
			</div>
		</</form:form>

	</div>
</body>
</html>