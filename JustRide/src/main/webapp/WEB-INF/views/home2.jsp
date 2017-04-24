<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Case</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="../resources/css/homescreen.css">
</link>

</head>

<body>

	<c:if test="${not empty error}">
   ${error}
</c:if>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="" style="font-size: 40px">JustRide</a>
			</div>
			<form action="myRides">
				<ul class="nav navbar-nav navbar-right">

					<c:if test="${not empty user}">
						<p align="right">
							<b>Welcome ${email}</b>
						</p>
					</c:if>
					<button type="submit" class="btn btn-primary"
						style="margin: 13px 12px 12px 10px; background-color: coral">My
						Rides</button>
					<button type="button" class="btn btn-primary"
						style="margin: 13px 12px 12px 10px; background-color: coral">Log
						Out</button>
			</form>
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