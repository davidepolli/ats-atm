<%@ include file="init.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"    
"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
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