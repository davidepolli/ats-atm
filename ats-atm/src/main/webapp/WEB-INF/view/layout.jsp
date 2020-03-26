<%@ include file="init.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"    
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
		<script src="https://code.jquery.com/jquery-3.4.1.min.js"integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
		
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
	</head>

	<body>
		<div class="container">
			<div class="row grow w-100">
				<div class="col-12 py-3">
					<tiles:insertAttribute name="header" />
				</div>
				<%--
				<div class="col-4 bg-info py-3">
					<tiles:insertAttribute name="menu" />
				</div>
				 --%>
				<div class="main col-12 h-100 py-3">
					<p class="mb-5">
						<tiles:insertAttribute name="body" />
					</p>
				</div>
			</div>
			<div class="row w-100">
				<div class="col-12 py-3">
					<tiles:insertAttribute name="footer" />
				</div>
			</div>
		</div>
	
	</body>
</html>