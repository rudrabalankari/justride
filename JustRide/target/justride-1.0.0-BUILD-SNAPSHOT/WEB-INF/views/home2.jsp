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


	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#" style="font-size: 40px">JustRide</a>
			</div>
			<ul class="nav navbar-nav navbar-right">

				<a href="#"><button type="submit" class="btn btn-primary"
						style="margin: 13px 12px 12px 10px;">Log In</button></a>
				<a href="#"><button type="submit" class="btn btn-primary"
						style="margin: 13px 12px 12px 10px;">Register</button></a>
			</ul>
		</div>
	</nav>


	<div class="container">
		<div class="col-md-4 col-md-offset-4">
			<div class="well">
				<p align="left" id="p01">Search for a car rental</p>
				<form action="/action_page.php">


					<div class="form-group">


						<select name="location" id=location>
							<option value="location">Select Location to pick up</option>
							<option value="location1">option1</option>
							<option value="location2">option2</option>
						</select>


					</div>


					<div class="form-group">

						<div id=iblo style="display: inline-block">
							<input type="date" name="pickup" class="form-control"
								style="width: 155px; height: 39px;">
						</div>
						<div style="display: inline-block">
							<select name="pickup-time" date-time-of="pickup" id="time">
								<option value="00:00">00:00 AM</option>
								<option value="00:30">00:30 AM</option>
								<option value="01:00">01:00 AM</option>
								<option value="01:30">01:30 AM</option>
							</select>
						</div>
					</div>



					<div class="form-group">

						<div id=iblo style="display: inline-block">
							<input type="date" name="pickup" class="form-control"
								style="width: 155px; height: 39px;">
						</div>
						<div style="display: inline-block">
							<select name="pickup-time" date-time-of="pickup" id="time">
								<option value="00:00">00:00 AM</option>
								<option value="00:30">00:30 AM</option>
								<option value="01:00">01:00 AM</option>
								<option value="01:30">01:30 AM</option>
							</select>
						</div>
					</div>



					<a href="#">
						<button type="submit" class="btn btn-primary">Search</button>
					</a>
			</div>

		</div>
		<!-- container-fluid -->
	</div>	</form>
	</body>

</html>