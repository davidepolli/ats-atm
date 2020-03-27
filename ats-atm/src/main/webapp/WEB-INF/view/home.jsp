<%@ include file="init.jsp"%>



<spring:url value="${findByCityServiceURL}" var="findByCityServiceURL" htmlEscape="true"/>
<spring:url value="${keywordFilterServiceURL}" var="keywordFilterServiceURL" htmlEscape="true"/>

<div class="row">
	<div class="col">
		<div class="card my-2">
			<div class="card-header"><spring:message code="rest.services"/></div>
			<div class="card-body">
				<table class="table table-striped table-responsive-lg">
					<thead>
						<tr>
							<th scope="col"><spring:message code="label.method"/></th>
							<th scope="col"><spring:message code="label.url"/></th>
							<th scope="col"><spring:message code="label.description"/></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><span class="btn btn-primary">GET</span></td>
							<td> http://localhost:8080/ats-atm/api/atm/all</td>
							<td><spring:message code="service.all.description"/></td>
						</tr>
						<tr>
							<td><span class="btn btn-primary">GET</span></td>
							<td>http://localhost:8080/ats-atm/api/atm/city/{cityName}</td>
							<td><spring:message code="service.city.description"/></td>
						</tr>
						
						<tr>
							<td><span class="btn btn-primary">GET</span></td>
							<td>http://localhost:8080/ats-atm/api/atm/filter?keyword={keyword}</td>
							<td><spring:message code="service.filter.description"/></td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
 
 
<div class="row">
	<div class="col">

		<div class="card my-2">

			<div class="card-header"><spring:message code="city.selection"/></div>
			<div class="card-body">
				
				<form id="citySelect" action="#" >
					
					<div class="form-group">
						<label for="city"><spring:message code="label.city"/>:</label>
						    <select name="selectedCity" class="form-control" id="city">
							<option value="" selected hidden="">
								<spring:message code="label.select.city"/>
							</option>
							<c:forEach var="cityName" items="${cities}">
								<option value="${cityName}">${cityName}</option>
							</c:forEach>
						</select>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<div class="col">

		<div class="card my-2">

			<div class="card-header"><spring:message code="label.free.search"/> </div>
			<div class="card-body">
			
				<form id="searchForm" action="#">
					<div class="form-group">
						<label for="search"><spring:message code="label.keyword"/>:</label>
						<input required="required" type="text" class="form-control" name="keyword">
					</div>
					<button type="submit" class="btn btn-primary"><spring:message code="label.submit"/></button>
				</form>				
				
			</div>
		</div>
	</div>
</div>
<div id="searchResult">
	<div class="row">
		<div class="col">
			<div class="card my-2">
				<div class="card-header">
					<span><spring:message code="search.result"/></span>
					<span class="ml-5">
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#jsonModal">
							<spring:message code="label.show.json"/>
						</button>
					 </span>
				</div>
	
				<div class="card-body">
					
					<table class="table table-striped table-responsive-lg">
						<thead>
							<tr>
								<th scope="col"><spring:message code="label.id"/></th>
								<th scope="col"><spring:message code="label.city"/></th>
								<th scope="col"><spring:message code="label.address"/></th>
								<th scope="col"><spring:message code="label.position"/></th>
								<th scope="col"><spring:message code="label.type"/></th>
							</tr>
						</thead>
						<tbody id="results">
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>




<div class="modal fade" id="jsonModal" tabindex="-1" role="dialog" aria-labelledby="jsonModal" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">JSON</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>




<script>
	jQuery(document).ready(function($) {
	
		
		$("#city").change(function(){
			
			cleanResults();
			
			var selectedCountry = $(this).children("option:selected").val();
	        var serviceURL = '${findByCityServiceURL}/'+selectedCountry;
			
			serviceCall(serviceURL);
		});
		
		
		$('#searchForm').on('submit', function (event) {
			event.preventDefault()
			
			cleanResults();
			
			var form_data = $(this).serialize();
			
			var serviceURL = '${keywordFilterServiceURL}?'+form_data;
			
			
			serviceCall(serviceURL);
		 
		});
		
	});
	
	
	function cleanResults() {
		$("#searchResult").hide();
		//$("#results").html("");
	}
	
	function populateSearchResult(data) {
		
		$("#results").html("");
		
		$.each(data, function(i, atm) {
			
			var address = atm.street + ' - ' + atm.housenumber + ', ' + atm.postalcode; 
			var position = 'lat ('+atm.lat+'), lng ('+atm.lng+')';
			
		    var row =  '<tr>'+
		    	'<td scope="row">' + atm.id + '</td>' +
		    	'<td>' + atm.city + '</td>' +
		    	'<td>' + address + '</td>' +
		    	'<td>' + position + '</td>' +
		    	'<td>' + atm.type + '</td>'
		    '</tr>';
		    
		    $("#results").append(row);
		    
		});
		
		$("#searchResult").show();
	}
	
	function populateJsonModal(formattedJson) {
		var modal = $("#jsonModal");
		modal.find('.modal-body').html(formattedJson);
	}
	
	  
	
	function serviceCall(serviceURL) {
		
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : serviceURL,
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				
				var formattedJson = JSON.stringify(data, null, '\t');
				
				// fill the results table
				populateSearchResult(data);
				// populate the modal with the raw json
				populateJsonModal(formattedJson);
				
			},
			error : function(e) {
				console.log("ERROR ["+e+"]");
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}
	
</script>