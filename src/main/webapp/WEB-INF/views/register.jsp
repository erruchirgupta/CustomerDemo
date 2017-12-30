<script type="text/javascript">
	function CheckCtype(val) {
		var element = document.getElementById('pan');
		if (val == 'General') {
			element.disabled = false;
			element.value = "";
		} else {
			element.disabled = true;
			element.value = "Not Required (for GEN only)";
		}

	}
</script>
<title>Customers Registration</title>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<body>
	<div class="container">
		<div class="well well-sm">
			<h3>Customer Registration form</h3>
		</div>
		<form:form method="post" cssClass="form-horizontal" action="addCustomer" id="addCustomer" modelAttribute="customer">
		<%-- <form class="form-horizontal" name="myForm"
			action="${pageContext.request.contextPath}/ProcessServlet"
			method="post" > --%>
			<%-- onSubmit="return validateForm()">--%>

			<div class="form-group">
				<input type="hidden" name="pageType" value="register"
					class="form-control">
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="fname"> First
					Name :</label>
				<div class="col-sm-4">
				<form:input path="fname" cssClass="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="lname"> Last Name
					:</label>
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
				<label class="control-label col-sm-2" for="dob"> Date of
					Birth :</label>
				<div class="col-sm-4">


				<form:input path="dob" type="date" cssClass="form-control" min="1980-01-01"
				pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|
			2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|
			1[02])-31))" />
				
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="ctype"> Type :</label>
				<div class="col-sm-10">
				
				<form:select cssClass="dropdowns" path="ctype" onchange='CheckCtype(this.value)'>
					<form:option value="General" label="General"/>
   					<form:option value="Minor" label="Minor"/>
   					<form:option value="Senior Citizen" label="Senior Citizen"/>
				</form:select>
				
				<!-- 	<div class="dropdowns">
						<select name="ctype" onchange='CheckCtype(this.value)'>
							<option value="General">General</option>
							<option value="Minor">Minor</option>
							<option value="Senior Citizen">Senior Citizen</option>
						</select>
					</div> -->
				</div>
			</div>

			<div class="form-group" id="panDiv">
				<label class="control-label col-sm-2" for="pan" id="panLabel">PAN
					:</label>
				<div class="col-sm-4">
					<form:input path="pan" cssClass="form-control"/>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="phoneno">Phone
					no. :</label>
				<div class="col-sm-4">
					<form:input path="phoneno" cssClass="form-control"/>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="address">Address
					:</label>
				<div class="col-sm-4">
					<form:input path="address" cssClass="form-control"/>
<!-- 					<input type="text" name="address" class="form-control"> -->
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="city">City :</label>
				<div class="col-sm-4">
					<form:input path="city" cssClass="form-control"/>
<!-- 					<input type="text" name="city" class="form-control"> -->
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="state">State :</label>
				<div class="col-sm-4">
					<form:input path="state" cssClass="form-control"/>
<!-- 					<input type="text" name="state" class="form-control"> -->
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="zip">Zip :</label>
				<div class="col-sm-4">
					<form:input path="zip" cssClass="form-control"/>
<!-- 					<input type="text" name="zip" class="form-control"> -->
				</div>
			</div>



					<form:hidden path="teller" cssClass="form-control"/>




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