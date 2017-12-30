<title>Customers Data</title>
<body>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>

	<c:set var="i" scope="page" value="${0}" />
	<div class="container">
		<div class="well well-sm">
			<h3>${status} Customers Data</h3>
		</div>

				<c:choose> 
					<c:when test="${customers eq '[]'}">
           <tr>
							<td colspan=5 style="text-align: center;">No data available</td>
						</tr>

					</c:when>
					<c:otherwise> 
    <table  class="table table-hover">
			<thead>
				<tr>
					<th align="center">Customers ID</th>
					<th align="center">Name</th>
					<th align="center">Email</th>
					<th align="center">Date of Birth</th>
					<th align="center">Type</th>
					<th align="center">Phone No.</th>
					<th align="center">State</th>
				<c:if test="${status eq 'Active'}">
					<th align="center" >Update  </th>
				</c:if>
					<th align="center">Action</th>
				</tr>
			</thead>
			<tbody>
     	<c:forEach var="customer" items="${customers}">
							<tr>
								<td>
								<a class="btn btn-primary btn-sm" role="button"
								href="${pageContext.request.contextPath}/searchCustomer/${customer.cid}" >
									${customer.cid}</a></td>
								<td>${customer.fname} ${customer.lname}</td>
								<td>${customer.email}</td>
								<td>${customer.dob}</td>
								<td>${customer.ctype}</td>
								<td>${customer.phoneno}</td>	
									
									
									<td>${customer.state}</td>
							
					<c:choose> 
					<c:when test="${status eq 'Active'}">
								<td><a class="btn btn-warning" role="button"
									href="${pageContext.request.contextPath}/updateCustomerPage/${customer.cid}" >Click</a>
								</td>
								<td><a class="btn btn-danger" role="button"
									href="${pageContext.request.contextPath}/deactivateCustomer/${customer.cid}">De-Activate</a>
								</td>
					</c:when>
					<c:otherwise> 
					<td><a class="btn btn-success" role="button"
									href="${pageContext.request.contextPath}/activateCustomer/${customer.cid}">Activate</a>
								</td>
					
					</c:otherwise>
				</c:choose>
								
<%--

								<td><a
									href="${pageContext.request.contextPath}<c:url value="\EmpListServlet?st=active&del=${employee.employeeId}"/>">Delete</a>
								</td>
 --%>								
								
							</tr>

						</c:forEach>

			</tbody>
		</table>
					</c:otherwise>
				</c:choose>






	</div>
</body>
</html>