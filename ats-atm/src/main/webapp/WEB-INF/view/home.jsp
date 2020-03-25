<%@ include file="init.jsp"%>

<%--
<h2><spring:message code="welcome.message"/></h2>
<form:form action="processForm" modelAttribute="student">


</form:form>
 --%>

<%--
<div class="row">
	<div class="col">
		<div class="card my-2">
			<div class="card-header">REST ATM Services</div>
			<div class="card-body">
				<table class="table table-striped table-responsive-lg">
					<thead>
						<tr>
							<th scope="col">METHOD</th>
							<th scope="col">URL</th>
							<th scope="col">Description</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a
								href="http://localhost:8080/evaluation-poc-back-end/api/ATMs/"
								class="btn btn-primary">GET</a></td>
							<td>http://localhost:8080/evaluation-poc-back-end/api/ATMs/</td>
							<td>Retrieve all the ATMs</td>
						</tr>
						<tr>
							<td><a
								href="http://localhost:8080/evaluation-poc-back-end/api/ATMs/"
								class="btn btn-primary">GET</a></td>
							<td>http://localhost:8080/evaluation-poc-back-end/api/ATMs/{cityName}</td>
							<td>Retrieve the city ATMs</td>
						</tr>

						<tr>
							<td><span class="btn btn-primary">GET</span></td>
							<td>http://localhost:8080/evaluation-poc-back-end/api/ATMs/{cityName}/{searchCriteria}</td>
							<td>Retrieve the city ATMs filtered by a criteria</td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
 --%>
 
 
<div class="row">
	<div class="col">

		<div class="card my-2">

			<div class="card-header"><spring:message code="city.selection"></spring:message></div>
			<div class="card-body">
				
				<form:form></form:form>
				
				<form action="${pageContext.request.contextPath}/atm/list"
					method="GET">
					<div class="row">
						<c:if test="${not empty(error)}">
							<div class="text-danger">${error}</div>
						</c:if>
					</div>
					<div class="form-group">
						<label for="city">City:</label> <select name="selectedCity"
							class="form-control" id="city">
							<c:choose>
								<c:when test="${not empty(selectedCity)}">
									<option value="${cityName}" selected="selected">${selectedCity}</option>
								</c:when>
								<c:otherwise>
									<option value="" selected hidden="">Choose here</option>
								</c:otherwise>
							</c:choose>
							<c:forEach var="cityName" items="${cities}">
								<option value="${cityName}">${cityName}</option>
							</c:forEach>
						</select>
					</div>

					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</div>
</div>
<c:if test="${not empty(selectedCity)}">
	<div class="row">
		<div class="col">
			<div class="card my-2">
				<div class="card-header">
					ATM in ${selectedCity}
					<c:if test="${not empty(searchCriteria)}">
						Search parameter : "${searchCriteria}"
						</c:if>
				</div>

				<div class="card-body">
					<form action="${pageContext.request.contextPath}/atm/search"
						method="GET">
						<div class="form-group">
							<label for="search">Search:</label> <input type="text"
								class="form-control" name="searchCriteria" id="search"
								value="${searchCriteria}">
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
					<table class="table table-striped table-responsive-lg">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">City</th>
								<th scope="col">Address</th>
								<th scope="col">Distance</th>
								<th scope="col">Type</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach varStatus="loop" var="atm" items="${atms}">
								<tr>
									<th scope="row">${loop.index + 1}</th>
									<td>${atm.address.city}-${atm.address.postalcode}</td>
									<td>${atm.address.street}${atm.address.housenumber},
										(${atm.address.geoLocation.lat},${atm.address.geoLocation.lng})</td>
									<td>${atm.distance}</td>
									<td>${atm.type}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

</c:if>
