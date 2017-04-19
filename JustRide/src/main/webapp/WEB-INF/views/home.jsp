<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>

<meta charset="utf-8"></meta>
<meta name="viewport" content="width=device-width, initial-scale=1"></meta>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="http://localhost:8080/justride/resources/css/homescreen.css">


</head>
<body>
	<c:if test="${not empty error}">
   ${error}
</c:if>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><font size="20">JustRide</font></a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right">
						<!-- <li> -->
						<div class="btn-group" role="group" aria-label="...">
							<a href="justride/login">
								<button type="button" class="btn btn-primary" style="margin: 13px 12px 12px 10px;background-color: coral">LOG IN</button>
							</a> <a href="justride/register">
								<button type="button" class="btn btn-primary" style="margin: 13px 12px 12px 10px;background-color: coral">REGISTER</button>
							</a>
						</div>
						<!-- </li> -->
					</ul>
				</div>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="col-md-4 col-md-offset-4">
			<div class="well">
				<p align="left" id="p01">Search for a car rental</p>
				<form action="carDataForm" method="get">
					<div class="form-group">
						<select name="location" id=location>
							<option value="UNC, Charlotte" selected="selected">UNC,
								Charlotte</option>
							<option value="Down Town">Down Town</option>
							<option value="Concord Mills">Concord Mills</option>
						</select>
					</div>
					<div>Select the Pickup Date and Time</div>
					<div class="form-group">
						<div id=iblo style="display: inline-block">
							<input type="date" name="pickupDate" class="form-control"
								style="width: 150px; height: 39px;">
						</div>
						<div id=iblo style="display: inline-block">
							<input type="time" name="pickupTime" class="form-control"
								style="width: 150px; height: 39px;">
						</div>
					</div>
					<div>Select the Drop Off Date and Time</div>
					<div class="form-group">

						<div id=iblo style="display: inline-block">
							<input type="date" name="dropoffDate" class="form-control"
								style="width: 150px; height: 39px;">
						</div>
						<div id=iblo style="display: inline-block">
							<input type="time" name="dropoffTime" class="form-control"
								style="width: 150px; height: 39px;">
						</div>
					</div>
					<a href="#">
						<button type="submit" class="btn btn-primary">Search</button>
					</a>
				</form>
			</div>
		</div>
		<!-- container-fluid -->
	</div>
</body>
</html>
