<title>Customers Details</title>
<body>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix ="form" %>
	<div class="container">
		<div class="well well-sm">
			<h3>Customer Details : ${customer.cid}   </h3><h3>              Added by: ${customer.teller} </h3>
		</div>
		
			<!-- onSubmit="return validateForm()">-->
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="fname">
					First Name :</label>
				<div class="col-sm-4">
				 <input type="text" disabled="disabled" name="fname" value="${customer.fname}" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="lname">
					Last Name :</label>
				<div class="col-sm-4">
					<input type="text" disabled="disabled" value="${customer.lname}" name="lname" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="email"> Email id
					:</label>
				<div class="col-sm-4">
					<input type="text" name="email"  disabled="disabled" value="${customer.email}"  class="form-control">
				</div>
			</div>
			
						<div class="form-group">
				<label class="control-label col-sm-2" for="dob"> Date of Birth
					:</label>
				<div class="col-sm-4">
			<input type="date" name="dob"  class="form-control" min="1980-01-01"  disabled="disabled" value="${customer.dob}"  pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|
			2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|
			1[02])-31))" required>
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="ctype">
					Type :</label>
				<div class="col-sm-4">
					<input type="text" name="ctype"  disabled="disabled" value="${customer.ctype}"  class="form-control">
				</div>
			</div>
			
			
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="phoneno">Phone no.
					:</label>
				<div class="col-sm-4">
					<input type="text" name="phoneno"  disabled="disabled" value="${customer.phoneno}" class="form-control">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="address">Address
					:</label>
				<div class="col-sm-4">
					<input type="text" name="address"  disabled="disabled" value="${customer.address}"  class="form-control">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="city">City
					:</label>
				<div class="col-sm-4">
					<input type="text" name="city"  disabled="disabled" value="${customer.city}"  class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="state">State
					:</label>
				<div class="col-sm-4">
					<input type="text" name="state"  disabled="disabled" value="${customer.state}"  class="form-control">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="zip">Zip
					:</label>
				<div class="col-sm-4">
					<input type="text" name="zip"  disabled="disabled" value="${customer.zip}"  class="form-control">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="pan">PAN
					:</label>
				<div class="col-sm-4">
					<input type="text" name="pan"  disabled="disabled" value="${customer.pan}"  class="form-control">
				</div>
			</div>
			


	</div>
</body>