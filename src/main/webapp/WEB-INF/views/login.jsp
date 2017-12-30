<title>User Login</title>
<body>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<div class="container">
		<div class="well well-sm">
			<h2>User Login</h2>
		</div>
		<form:form cssClass="form-horizontal" method="post" action="login"
			id="login" modelAttribute="users">
			<%-- <form class="form-horizontal" name="myForm"
			action="${pageContext.request.contextPath}/ProcessServlet"
			method="post" onSubmit="return validateForm()"> --%>

			<div class="form-group">
				<input type="hidden" name="pageType" value="login"
					class="form-control">
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="user">User Name :</label>
				<div class="col-sm-4">
					<form:input path="name" cssClass="form-control" />
					<!-- <input type="text" name="user" class="form-control"> -->
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="password">Password
					:</label>
				<div class="col-sm-4">
					<form:input path="pass" cssClass="form-control" />
					<!-- 					<input type="password" name="password" class="form-control"> -->
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Submit</button>
				</div>
			</div>
		</form:form>
		<%-- </form> --%>

		<div>
			<a type="button" class="btn btn-danger" href="#" id="changePwd">Modal
				Call</a>
		</div>

	</div>

	<div class="modal fade" id="messageModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div id="message_header" class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">�</button>
					<h4 class="modal-title" id="message_title">Modal title</h4>
				</div>
				<div class="modal-body">
					<p id="message_content">One fine body</p>
				</div>
				<div class="modal-footer">
					<button type="button" id="message_ok" class="btn"
						data-dismiss="modal">Ok</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<script src="js/async_ajax_account.js"></script>
	</div>
</body>