<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="http://localhost:8080/justride/resources/css/viewcars.css">



<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>


<script type="text/javascript">
	function openInNewTab(url) {
		var win = window.open(url, '_blank');
		win.focus();
	}
</script>
</head>
<body>
	<div class="container text-center">
		<div class="row">
			<div class="col-sm-2 well">
				<h4 style="font-weight: bold;">Filter Your Result</h4>

				<!--checkboxes-->
				<form method="get" action="filters">
					<div class="well">
						<p>Location</p>

						<div class="checkbox">
							<label><input name="locationName" type="checkbox"
								value="UNC, Charlotte">UNC, Charlotte</label>
						</div>
						<div class="checkbox">
							<label><input name="locationName" type="checkbox"
								value="Down Town">Down Town</label>
						</div>
						<div class="checkbox">
							<label><input name="locationName" type="checkbox"
								value="Concord  Mills">Concord Mills</label>
						</div>
					</div>

					<div class="well">
						<p>Car Type</p>

						<div class="checkbox">
							<label><input name="category" type="checkbox"
								value="Economy">Economy</label>
						</div>
						<div class="checkbox">
							<label><input name="category" type="checkbox"
								value="Moderate">Moderate</label>
						</div>
						<div class="checkbox">
							<label><input name="category" type="checkbox"
								value="Premium">Premium</label>
						</div>

					</div>

					<div class="well">
						<p>no of Seats</p>
						<div class="checkbox">
							<label><input name="seats" type="checkbox" value="5">5</label>
						</div>
						<div class="checkbox">
							<label><input name="seats" type="checkbox" value="7">7</label>
						</div>
					</div>

					<button type="submit" class="btn btn-primary">Search</button>
				</form>
			</div>

			<!--Car details-->

			<div class="col-sm-10 col-sm-offset-0 well">
				<p align="left" id="p01">
					Location(s) Applied

					<c:forEach items="${locationList}" var="location">
						<b>${location} &nbsp; &nbsp; &nbsp; &nbsp;</b>
					</c:forEach>

					Total Cars Found ${carList.size()}
				</p>
				<div class="panel panel-default">
					<c:forEach items="${carList}" var="car">

						<div class="panel-heading">${car.getCarName()}${car.carId}</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-sm-4">
									<p>
										<img src="${car.imgName}" class="img-responsive left"
											style="width: 100%">
									<ul class="type">
										<li class="icon"><span class="glyphicon glyphicon-user"
											id="span01" style="font-size: 15px;">Seats
												${car.seats}</span></li>
										<li><span class="glyphicon glyphicon-ok" id="span01"
											style="font-size: 15px;">AC</span></li>
									</ul>
									</p>
								</div>

								<div class="col-sm-4">
									<ul class="type" style="height: 20px;">
										<li
											style="font-size: 15px; padding: 5px; float: center; font-weight: bold;">Location</span></li>
										<li><span class="glyphicon glyphicon-map-marker"
											id="span01"
											style="padding: 0px; font-size: 17px; word-spacing: -10px;">${car.location}</span></li>
									</ul>
								</div>

								<div class="col-sm-6 well"
									style="float: center; height: 200px; width: 250px; line-height: 500%;">
									<div class="type" style="position: relative; font-size: 25px;">
										${car.costPerHour}$/hour</div>
									<button type="button" value="${car.carId}"
										class="btn btn-primary" style="width: 100px">Continue</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<a
					onclick="openInNewTab('https://www.google.com/maps/place/University+of+North+Carolina+at+Charlotte/@35.3070929,-80.7373527,17z/data=!3m1!4b1!4m5!3m4!1s0x88541c1ad0a92357:0x6b3f67f7f19324f3!8m2!3d35.3070929!4d-80.735164');">
					<button type="button" class="btn btn-primary" style="width: 100px">Continue</button>
				</a>

			</div>

		</div>
	</div>




</body>
</html>